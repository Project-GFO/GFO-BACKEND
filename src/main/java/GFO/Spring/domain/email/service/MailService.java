package GFO.Spring.domain.email.service;

import GFO.Spring.domain.email.presentation.dto.EmailDto;
import GFO.Spring.domain.email.entity.EmailVerify;
import GFO.Spring.domain.email.exception.MailAuthExpiredException;
import GFO.Spring.domain.email.exception.NotMatchAuthCodeException;
import GFO.Spring.domain.email.repository.EmailVerifyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.Random;


@Service
@RequiredArgsConstructor
@EnableAsync
public class MailService {

    private final JavaMailSender emailSender;
    private final EmailVerifyRepository emailVerifyRepository;

    @Async
    public void sendMail(EmailDto emailDto){
        Random random = new Random();
        String verifyCode = String.valueOf(random.nextInt(9999)+1111);
        sendAuthCode(emailDto.getEmail(),verifyCode);
    }

    private void sendAuthCode(String email, String verifyCode){
        String title = "GFO 회원가입 이메일 인증번호";
        String text = "GFO 회원가입 인증번호는 " + verifyCode + "입니다. 돌아가셔서 입력해주세요";
        EmailVerify emailVerify = emailVerifyRepository.findById(email)
                .orElse(EmailVerify.builder()
                        .verification(false)
                        .randomValue(verifyCode)
                        .email(email)
                        .build());
    }

    public void verificationMail(String email, String verifyCode){
        EmailVerify emailVerifyEntity = emailVerifyRepository.findById(email)
                .orElseThrow(()-> new MailAuthExpiredException("인증코드가 만료되었습니다."));
        if(emailVerifyEntity.getRandomValue() != verifyCode){
            throw new NotMatchAuthCodeException("인증코드가 일치하지 않습니다.");
        }
        emailVerifyEntity.updateVerification(true);
        emailVerifyRepository.save(emailVerifyEntity);
    }
}
