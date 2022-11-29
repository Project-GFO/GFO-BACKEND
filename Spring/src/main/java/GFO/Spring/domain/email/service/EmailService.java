package GFO.Spring.domain.email.service;

import GFO.Spring.domain.user.User;
import GFO.Spring.domain.user.presentation.dto.UserDto;
import GFO.Spring.domain.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

public interface EmailService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public default User signup(UserDto userDto){
        // 중복 처리
        if(userRepository.findByUserEmail(userDto.getUserEmail()).orElse(null) != null){
            throw new RuntimeException("이미 가입된 유저입니다.");
        }

        User user = User.builder()
                .userEmail(userDto.getUserEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .userName(userDto.getUserName())
                .build();

        return userRepository.save(user);
    }

    @Transactional
    public void updateAuthStatus(String email) {
        User user = userRepository.findByUserEmail(email).orElseThrow();
        user.setAuthStatus(true);
    }
}
