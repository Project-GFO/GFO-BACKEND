package GFO.Spring.domain.user.presentation.dto.response;

import GFO.Spring.domain.user.User;
import lombok.Getter;

@Getter
public class UserResponse {
    private final String email;
    private final String name;
    private final String duty;
    private final Integer classNum;

    public UserResponse(String email, String name, String duty, Integer classNum) {
        this.email = email;
        this.name = name;
        this.duty = duty;
        this.classNum = classNum;
    }

    public static UserResponse of(User user) {
        return new UserResponse(
                user.getEmail(),
                user.getName(),
                user.getDuty(),
                user.getClassNum());
    }
}
