package GFO.Spring.domain.user.entity;

import GFO.Spring.domain.user.enums.Role;
import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@Table(name = "member")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Role role;
    @PrePersist
    public void setting() {
        this.role = this.role == null ? Role.STUDENT : this.role;
    }
}
