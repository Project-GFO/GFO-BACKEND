package GFO.Spring.domain.email.service.impl;

import GFO.Spring.domain.email.presentation.dto.request.EmailSendDto;
import GFO.Spring.domain.email.repository.EmailAuthRepository;
import GFO.Spring.domain.email.service.EmailSendService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@EnableAsync
@RequiredArgsConstructor
public class EmailSendServiceImpl implements EmailSendService {
    private final EmailAuthRepository emailAuthRepository;
    private final JavaMailSender javaMailSender;

    @Async
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void execute(EmailSendDto emailSendDto) {

    }
}
