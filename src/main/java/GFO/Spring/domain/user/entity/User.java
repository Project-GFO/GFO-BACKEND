package GFO.Spring.domain.user.entity;

import GFO.Spring.domain.post.entity.Post;
import GFO.Spring.domain.user.enums.Role;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
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
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "user")
    private List<Post> post = new ArrayList<>();

    @PrePersist
    public void setting() {
        this.role = this.role == null ? Role.STUDENT : this.role;
    }
}
