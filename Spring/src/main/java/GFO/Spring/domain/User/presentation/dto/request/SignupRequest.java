package GFO.Spring.domain.User.presentation.dto.request;

import GFO.Spring.domain.User.User;
import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.validation.constraints.*;

@Getter
@NoArgsConstructor
public class SignupRequest {

    @Email
    @NotEmpty(message = "이메일은 필수 입력값입니다")
    private String email;
    @NotEmpty(message = "이름은 필수 입력값입니다")
    private String name;
    @NotEmpty(message = "비밀번호는 필수 입력값입니다")
    private String password;
    @NotEmpty(message = "필수 입력란입니다")
    private String duty;
    @NotEmpty(message = "학번은 필수 입력값입니다")
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
