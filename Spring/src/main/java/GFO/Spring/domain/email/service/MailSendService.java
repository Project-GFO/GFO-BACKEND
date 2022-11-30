package GFO.Spring.domain.email.service;

import GFO.Spring.domain.user.User;
import GFO.Spring.domain.user.presentation.dto.UserDto;
import GFO.Spring.domain.user.repository.UserRepository;
import GFO.Spring.global.MailUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class MailSendService {
    private JavaMailSenderImpl mailSender;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    // 난수의 크기 변수
    private int size;
    //인증 키 생성
    private String getKey(int size){
        this.size = size;
        return getAuthCode();
    }

    //인증코드 발생
    private String getAuthCode() {
        Random random = new Random();
        return String.valueOf(random.nextInt(888888) + 111111);
    }

    //인증메일 보내기
    public String sendAuthMail(String email) {
        //6자리 난수 인증번호 생성
        String authKey = getAuthCode();

        try {
            MailUtils sendMail = new MailUtils(mailSender);
            sendMail.setSubject("회원가입 이메일 인증");
            sendMail.setText(new StringBuffer().append("<h1>[이메일 인증]</h1>")
                    .append("<p>아래 링크를 클릭하시면 이메일 인증이 완료됩니다.</p>")
                    .append("<a href='http://localhost:8080/user/signup-confirm?email=")
                    .append(email)
                    .append("' target='_blenk'>이메일 인증 확인</a>")
                    .toString());
            sendMail.setFrom("s22055@gsm.hs.kr" , "채종인");
            sendMail.setTo(email);
            sendMail.send();
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return authKey;
    }

    @Transactional
    public User signup(UserDto userDto){
        // 중복 처리
        if(userRepository.findByUserEmail(userDto.getUserEmail()).orElse(null) != null){
            throw new RuntimeException("이미 가입된 유저입니다.");
        }

        User user = User.builder()
                .email(userDto.getUserEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .name(userDto.getUserName())
                .build();

        return userRepository.save(user);
    }

    @Transactional
    public void updateAuthStatus(String email) {
        User user = userRepository.findByUserEmail(email).orElseThrow();
        user.setAuthStatus(true);
    }

}
