package GFO.Spring.domain.User.presentation.dto.request;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SignupRequestDto {
    private String email;
    private String name;
    private String password;
    private String duty;
    private int classNum;
}
