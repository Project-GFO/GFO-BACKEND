package GFO.Spring.domain.User.presentation.dto.response;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SignupResponseDto {
    private String email;
    private String name;
    private String password;
    private String duty;
    private int classNum;
}
