package GFO.Spring.domain.User.presentation.dto.request;

import GFO.Spring.domain.User.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


import javax.validation.constraints.*;

@Getter
@RequiredArgsConstructor
public class SignupRequest {

    @Email
    @NotBlank(message = "이메일은 필수 입력값입니다")
    private final String email;
    @NotBlank(message = "이름은 필수 입력값입니다")
    private final String name;
    @NotBlank(message = "비밀번호는 필수 입력값입니다")
    private final String password;
    @NotBlank(message = "필수 입력란입니다")
    private final String duty;
    @NotEmpty(message = "학번은 필수 입력값입니다")
    private final int classNum;

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
