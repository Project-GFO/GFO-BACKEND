package GFO.Spring.domain.user.repository;

import GFO.Spring.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SignupRepository extends JpaRepository<User, String> {
    Boolean existsByEmail(String email);

    Boolean existsByClassNum(int classNum);

}