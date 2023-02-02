package GFO.Spring.domain.email.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmailSendRequest {
    @Email
    @NotBlank(message = "이메일은 필수 입력값입니다")
    private String email;
}
