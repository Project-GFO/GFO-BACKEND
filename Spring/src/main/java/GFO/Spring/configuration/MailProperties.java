package GFO.Spring.configuration;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@AllArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "spring.mail")
public class MailProperties {
    //커밋 실수로 주석한번 답니다.
    private String host;
    private Integer port;
    private String username;
    private String password;
}
