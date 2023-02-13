package GFO.Spring.domain.user.entity;

import GFO.Spring.domain.user.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


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


    private Boolean verificationStatus;

    public void updatePassword(String password){
        this.password = password;
    }

    public void updateVerification(Boolean verificationStatus){
        this.verificationStatus = verificationStatus;
    }
    @PrePersist
    public void setting() {
        this.role = this.role == null ? Role.STUDENT : this.role;
    }
}
