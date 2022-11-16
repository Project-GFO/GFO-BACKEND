package GFO.Spring.global.security.jwt;

import lombok.Getter;

@Getter
public class Subject {
    private final String email;
    private final String name;
    private final String duty;
    private final Integer classNum;

    private final String type;

    public Subject(String email, String name, String duty, Integer classNum, String type) {
        this.email = email;
        this.name = name;
        this.duty = duty;
        this.classNum = classNum;
        this.type = type;
    }

    public Subject atk(String email, String name, String duty, Integer classNum) {
        return new Subject(email, name, duty, classNum, "ATK");
    }

    public Subject rtk(String email, String name, String duty, Integer classNum) {
        return new Subject(email, name, duty, classNum, "RTK");
    }
}
