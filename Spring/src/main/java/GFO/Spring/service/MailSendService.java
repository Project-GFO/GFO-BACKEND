package GFO.Spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message.RecipientType;
import javax.mail.internet.MimeMessage;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MailSendService implements EmailService{

    private final JavaMailSender emailSender;

    public static final StringBuffer key = new StringBuffer();
    public static final Random random = new Random();
    public static final String ePw = createKey();

    private MimeMessage createMessage(String to)throws Exception{
        System.out.println("ePw = " + ePw);
        System.out.println("보내는 대상 : "+ to);
        System.out.println("인증 번호 : "+ePw);
        MimeMessage  message = emailSender.createMimeMessage();
        System.out.println("RecipientType.TO = " + RecipientType.TO);
        message.addRecipients(RecipientType.TO, to);//보내는 대상
        message.setSubject("G4 회원가입 이메일 인증");//제목

        String msgg="";
        msgg+= "<div style='margin:100px;'>";
        msgg+= "<h1> 안녕하세요 GFO입니다. </h1>";
        msgg+= "<br>";
        msgg+= "<p>아래 코드를 회원가입 창으로 돌아가 입력해주세요<p>";
        msgg+= "<br>";
        msgg+= "<p>감사합니다<p>";
        msgg+= "<br>";
        msgg+= "<div align='center' style='border:1px solid black; font-family:verdana';>";
        msgg+= "<h3 style='color:blue;'>회원가입 인증 코드입니다.</h3>";
        msgg+= "<div style='font-size:130%'>";
        msgg+= "CODE : <strong>";
        msgg+= ePw+"</strong><div><br/> ";
        msgg+= "</div>";
        message.setText(msgg, "utf-8", "html");//내용

        return message;
    }

    public static String createKey() {
        String[] split = UUID.randomUUID().toString().split("-");
        return split[1]+split[0];
    }
    @Override
    public String sendSimpleMessage(String to)throws Exception {
        MimeMessage message = createMessage(to);
        try{//예외처리
            emailSender.send(message);
        }catch(MailException es){
            es.printStackTrace();
            throw new IllegalArgumentException();
        }
        return ePw;
    }

}
