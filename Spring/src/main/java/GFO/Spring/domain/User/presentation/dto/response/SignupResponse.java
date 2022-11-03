package GFO.Spring.domain.User.presentation.dto.response;

import GFO.Spring.domain.User.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignupResponse {
    private String email;
    private String name;
    private String password;
    private String duty;
    private int classNum;

    public SignupResponse(User user) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.duty = duty;
        this.classNum = classNum;
    }
}
