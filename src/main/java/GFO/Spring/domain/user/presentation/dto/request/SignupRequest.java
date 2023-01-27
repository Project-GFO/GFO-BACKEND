package GFO.Spring.domain.user.presentation.dto.request;

import lombok.Getter;
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
    private final Integer classNum;
}
