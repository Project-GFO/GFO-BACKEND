package GFO.Spring.domain.User.presentation.dto.response;

import lombok.Getter;

@Getter
public class SignupResponse {
    private String email;
    private String name;
    private String password;
    private String duty;
    private int classNum;

}
