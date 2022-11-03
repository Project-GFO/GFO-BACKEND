package GFO.Spring.domain.User.presentation.dto.request;

import GFO.Spring.domain.User.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignupRequest {
    private String email;
    private String name;
    private String password;
    private String duty;
    private int classNum;

    public User toEntity() {
        return User.builder()
                .email(email)
                .name(name)
                .password(password)
                .duty(duty)
                .classNum(classNum)
                .build();
    }
}
