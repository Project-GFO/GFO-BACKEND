package GFO.Spring.global.configuration.email;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@RequiredArgsConstructor
class EmailConfig {
    private final MailProperties mailProperties;

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl smtp = new JavaMailSenderImpl();
        smtp.setHost(mailProperties.getHost());
        smtp.setPort(mailProperties.getPort());
        smtp.setUsername(mailProperties.getUsername());
        smtp.setPassword(mailProperties.getPassword());
        smtp.getJavaMailProperties().put("mail.smtp.auth", true);
        smtp.getJavaMailProperties().put("mail.smtp.connectiontimeout", 5000);
        smtp.getJavaMailProperties().put("mail.smtp.timeout", 5000);
        smtp.getJavaMailProperties().put("mail.smtp.writetimeout", 5000);
        smtp.getJavaMailProperties().put("mail.transport.protocol", "smtp");
        smtp.getJavaMailProperties().put("mail.smtp.starttls.enable", true);
        smtp.getJavaMailProperties().put("mail.smtp.starttls.required", true);
        return smtp;
    }

}