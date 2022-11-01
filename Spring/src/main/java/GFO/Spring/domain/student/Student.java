package GFO.Spring.domain.student;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Getter @NoArgsConstructor @Entity
public class Student {
    @Id
    private String email;   // Primary Key

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String duty;

    @Column(nullable = false)
    private int classnum;
}
