package GFO.Spring.domain.email.service.impl;

import GFO.Spring.domain.email.entity.EmailAuth;
import GFO.Spring.domain.email.exception.EmailSendFailedException;
import GFO.Spring.domain.email.exception.ManyRequestEmailAuthException;
import GFO.Spring.domain.email.presentation.dto.request.EmailSendDto;
import GFO.Spring.domain.email.repository.EmailAuthRepository;
import GFO.Spring.domain.email.service.EmailSendService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailSendServiceImpl implements EmailSendService {
    private final EmailAuthRepository emailAuthRepository;
    private final JavaMailSender javaMailSender;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void execute(EmailSendDto emailSendDto) {
        Random random = new Random();
        String authKey = String.valueOf(random.nextInt(8888)+1111);

        sendAuthEmail(emailSendDto.getEmail(), authKey);
    }

    private void sendAuthEmail(String email, String authKey) {
        String subject = "GFO 인증번호";
        String content = "회원 가입을 위한 인증번호는" + authKey + "입니다 <br />";
        EmailAuth emailAuth = EmailAuth.builder()
                .email(email)
                .randomValue(authKey)
                .attemptCount(0)
                .authentication(false)
                .build();
        if(emailAuth.getAttemptCount() >= 3) {
            throw new ManyRequestEmailAuthException("이메일은 15분에 최대 3회까지 요청 가능합니다");
        }
        emailAuth.updateRandomValue(authKey);
        emailAuth.increaseAttemptCount();

        emailAuthRepository.save(emailAuth);
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8");
            helper.setTo(email);
            helper.setSubject(subject);
            helper.setText(content,true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new EmailSendFailedException("이메일 발송에 실패하였습니다");
        }
    }
}
