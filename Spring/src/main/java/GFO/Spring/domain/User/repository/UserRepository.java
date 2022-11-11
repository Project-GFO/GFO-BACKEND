package GFO.Spring.domain.User.repository;

import GFO.Spring.domain.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    Boolean existsByEmail(String email);

    Boolean existsByClassNum(int classNum);
}