package GFO.Spring.domain.user.repository;

import GFO.Spring.domain.user.entity.BlackList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlackListRepository extends JpaRepository<BlackList, String> {
}
