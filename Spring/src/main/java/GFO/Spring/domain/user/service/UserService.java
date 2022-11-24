package GFO.Spring.domain.user.service;

import GFO.Spring.domain.user.User;
import GFO.Spring.domain.user.exception.DuplicatedUserClassNumException;
import GFO.Spring.domain.user.exception.DuplicatedUserEmailException;
import GFO.Spring.domain.user.presentation.dto.request.SignupRequest;
import GFO.Spring.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

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
}
