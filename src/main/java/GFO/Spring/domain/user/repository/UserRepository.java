package GFO.Spring.domain.user.repository;

import GFO.Spring.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Boolean existsByEmail(String email);
    Optional<User> findUserByEmail(String email);
}