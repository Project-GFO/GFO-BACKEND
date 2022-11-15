package GFO.Spring.domain.user;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity @Getter
@Table(name = "member")
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
    private Integer classNum;
}
