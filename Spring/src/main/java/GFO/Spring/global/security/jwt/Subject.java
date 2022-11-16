package GFO.Spring.global.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Subject {
    private final String email;
    private final String name;
    private final String duty;
    private final Integer classNum;

    private final String type;

    public static Subject accessToken(String email, String name, String duty, Integer classNum) {
        return new Subject(email, name, duty, classNum, "ATK");
    }

    public static Subject refreshToken(String email, String name, String duty, Integer classNum) {
        return new Subject(email, name, duty, classNum, "RTK");
    }
}
