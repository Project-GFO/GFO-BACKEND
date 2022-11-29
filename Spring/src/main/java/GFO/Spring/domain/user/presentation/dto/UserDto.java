package GFO.Spring.domain.user.presentation.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @Email
    @NotNull
    private String userEmail;

    @NotNull
    private String password;

    @NotNull
    @Size(min = 3, max = 30)
    private String userName;

    private String authKey;
}
