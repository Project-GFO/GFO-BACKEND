package GFO.Spring.domain.user.service;

import GFO.Spring.domain.user.entity.BlackList;
import GFO.Spring.domain.user.entity.RefreshToken;
import GFO.Spring.domain.user.entity.User;
import GFO.Spring.domain.user.exception.exceptioncollection.*;
import GFO.Spring.domain.user.presentation.dto.request.SignInRequest;
import GFO.Spring.domain.user.presentation.dto.request.SignUpRequest;
import GFO.Spring.domain.user.presentation.dto.response.NewTokenResponse;
import GFO.Spring.domain.user.presentation.dto.response.SignInResponse;
import GFO.Spring.domain.user.repository.BlackListRepository;
import GFO.Spring.domain.user.repository.RefreshTokenRepository;
import GFO.Spring.domain.user.repository.UserRepository;
import GFO.Spring.global.exception.exceptioncollection.TokenNotValidException;
import GFO.Spring.global.security.jwt.JwtProvider;
import GFO.Spring.global.security.jwt.properties.JwtProperties;
import GFO.Spring.global.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final BlackListRepository blackListRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final UserUtil userUtil;
    private final RedisTemplate redisTemplate;

    private final JwtProperties jwtProperties;

    @Transactional(rollbackFor = Exception.class)
    public void signUp(SignUpRequest signupRequest) {
      if(userRepository.existsByEmail(signupRequest.getEmail())) {
          throw new DuplicatedUserEmailException("이메일이 중복되었습니다");
      }
      if (userRepository.existsByClassNum(signupRequest.getClassNum())) {
          throw new DuplicatedUserClassNumException("학번이 중복되었습니다");
      }
      User user = User.builder()
              .email(signupRequest.getEmail())
              .name(signupRequest.getName())
              .password(passwordEncoder.encode(signupRequest.getPassword()))
              .classNum(signupRequest.getClassNum())
              .duty(signupRequest.getDuty())
              .build();
      userRepository.save(user);
    }

    @Transactional(rollbackFor = Exception.class)
    public SignInResponse signIn(SignInRequest signinRequest) {
        User user = userRepository
                .findUserByEmail(signinRequest.getEmail())
                .orElseThrow(()->new EmailNotFoundException("이메일을 찾지 못했습니다"));
        if(!passwordEncoder.matches(signinRequest.getPassword(), user.getPassword())){
            throw new WrongPasswordException("비밀번호가 올바르지 않습니다");
        }
        String accessToken = jwtProvider.generatedAccessToken(signinRequest.getEmail());
        String refreshToken = jwtProvider.generatedRefreshToken(signinRequest.getEmail());
        RefreshToken entityToRedis = new RefreshToken(signinRequest.getEmail(), refreshToken, jwtProvider.getREFRESH_TOKEN_EXPIRE_TIME());
        refreshTokenRepository.save(entityToRedis);
        return SignInResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .expiredAt(jwtProvider.getExpiredAtToken())
                .build();
    }

    @Transactional(rollbackFor = Exception.class)
    public void logout(String accessToken) {
        User user = userUtil.currentUser();
        RefreshToken refreshToken = refreshTokenRepository.findRefreshTokenByEmail(user.getEmail()).orElseThrow(()-> new RefreshTokenNotFoundException("리프레시 토큰을 찾을 수 없습니다"));
        refreshTokenRepository.delete(refreshToken);
        saveBlackList(user.getEmail(), accessToken);
    }

    @Transactional(rollbackFor = Exception.class)
    private void saveBlackList(String email, String accessToken) {
        if(redisTemplate.opsForValue().get(accessToken)!=null) {
            throw new BlackListAlreadyExistException("블랙리스트에 이미 등록되어있습니다");
        }
        BlackList blackList = BlackList.builder()
                .email(email)
                .accessToken(jwtProvider.validateTokenType(accessToken))
                .timeToLive(jwtProvider.getExpiredAtTokenToLong())
                .build();
        blackListRepository.save(blackList);
    }

    @Transactional(rollbackFor = Exception.class)
    public NewTokenResponse tokenReissue(String refreshToken) {
        String email = jwtProvider.getTokenEmail(refreshToken, jwtProperties.getRefreshSecret());

        RefreshToken token = refreshTokenRepository.findRefreshTokenByEmail(email).orElseThrow(()-> new RefreshTokenNotFoundException("리프레시 토큰이 존재하지 않습니다"));

        if(!token.getToken().equals(refreshToken)) {
            throw new TokenNotValidException("토큰이 유효하지 않습니다");
        }
        String access = jwtProvider.generatedAccessToken(email);
        String refresh = jwtProvider.generatedRefreshToken(email);
        ZonedDateTime expiredAt = jwtProvider.getExpiredAtToken();
        token.exchangeRefreshToken(refreshToken);
        refreshTokenRepository.save(token);

        return NewTokenResponse.builder()
                .accessToken(access)
                .refreshToken(refresh)
                .expiredAt(expiredAt)
                .build();
    }
}
