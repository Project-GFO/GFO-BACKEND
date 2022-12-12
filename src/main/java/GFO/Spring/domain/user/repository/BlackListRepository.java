package GFO.Spring.domain.user.repository;

import GFO.Spring.domain.user.entity.BlackList;
import org.springframework.data.repository.CrudRepository;

public interface BlackListRepository extends CrudRepository<BlackList, String> {
}
