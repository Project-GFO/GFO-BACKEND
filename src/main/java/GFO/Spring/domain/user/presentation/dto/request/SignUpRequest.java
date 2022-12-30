package GFO.Spring.domain.user.presentation.dto.request;

import GFO.Spring.domain.user.enums.Role;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


import javax.validation.constraints.*;

@Getter
@RequiredArgsConstructor
public class SignUpRequest {

    @Email
    @NotBlank(message = "이메일은 필수 입력값입니다")
    private final String email;
    @NotBlank(message = "이름은 필수 입력값입니다")
    private final String name;
    @NotBlank(message = "비밀번호는 필수 입력값입니다")
    private final String password;
    private final Role role;
}
