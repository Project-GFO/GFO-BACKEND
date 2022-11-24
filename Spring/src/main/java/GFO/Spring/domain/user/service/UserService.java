package GFO.Spring.domain.user.service;

import GFO.Spring.domain.user.entity.RefreshToken;
import GFO.Spring.domain.user.entity.User;
import GFO.Spring.domain.user.exception.exceptioncollection.DuplicatedUserClassNumException;
import GFO.Spring.domain.user.exception.exceptioncollection.DuplicatedUserEmailException;
import GFO.Spring.domain.user.exception.exceptioncollection.EmailNotFoundException;
import GFO.Spring.domain.user.exception.exceptioncollection.WrongPasswordException;
import GFO.Spring.domain.user.presentation.dto.request.SigninRequest;
import GFO.Spring.domain.user.presentation.dto.request.SignupRequest;
import GFO.Spring.domain.user.presentation.dto.response.SignInResponse;
import GFO.Spring.domain.user.repository.RefreshTokenRepository;
import GFO.Spring.domain.user.repository.UserRepository;
import GFO.Spring.global.security.jwt.JwtProvider;
import GFO.Spring.global.security.jwt.properties.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final JwtProperties jwtProperties;

    @Transactional(rollbackFor = Exception.class)
    public void signUp(SignupRequest signupRequest) {
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
              .duty(signupRequest.getDuty())
              .classNum(signupRequest.getClassNum())
              .build();
      userRepository.save(user);
    }

    @Transactional(rollbackFor = Exception.class)
    public SignInResponse signIn(SigninRequest signinRequest) {
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
                .expiredAt(jwtProvider.getExpiredAtToken(accessToken, jwtProperties.getAccessSecret()))
                .build();
    }
}
