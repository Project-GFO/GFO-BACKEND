package GFO.Spring.domain.email.service.impl;

import GFO.Spring.domain.email.entity.EmailAuth;
import GFO.Spring.domain.email.exception.AuthCodeMismatchException;
import GFO.Spring.domain.email.repository.EmailAuthRepository;
import GFO.Spring.domain.email.service.EmailAuthService;
import GFO.Spring.domain.user.exception.exceptioncollection.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EmailCheckServiceImpl implements EmailAuthService {
    private final EmailAuthRepository emailAuthRepository;

    @Override
    public void execute(String email, String authKey) {
        EmailAuth emailAuth = emailAuthRepository.findById(email).orElseThrow(() -> new UserNotFoundException("유저를 찾을 수 없습니다"));
        checkAuthKey(emailAuth, authKey);
        emailAuth.updateAuthentication(true);
        emailAuthRepository.save(emailAuth);
    }

    private void checkAuthKey(EmailAuth emailAuth, String authKey) {
        if(!Objects.equals(emailAuth.getRandomValue(), authKey))
            throw new AuthCodeMismatchException("인증번호가 일치하지 않습니다");
    }
}
