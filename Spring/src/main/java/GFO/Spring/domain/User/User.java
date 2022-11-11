package GFO.Spring.domain.User;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity @Getter
@Table(name = "User")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    private String email;   // Primary Key

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String duty;

    @Column(nullable = false)
    private int classNum;
}
