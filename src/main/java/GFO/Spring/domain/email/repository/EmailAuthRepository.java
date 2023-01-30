package GFO.Spring.domain.email.repository;

import GFO.Spring.domain.email.entity.EmailAuth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailAuthRepository extends JpaRepository<EmailAuth, String> {
}
