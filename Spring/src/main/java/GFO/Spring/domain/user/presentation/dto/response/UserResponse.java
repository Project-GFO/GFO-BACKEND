package GFO.Spring.domain.user.presentation.dto.response;

import GFO.Spring.domain.user.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserResponse {
    private final String email;
    private final String name;
    private final String duty;
    private final Integer classNum;

    public static UserResponse of(User user) {
        return new UserResponse(
                user.getEmail(),
                user.getName(),
                user.getDuty(),
                user.getClassNum());
    }
}
