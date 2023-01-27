package GFO.Spring.domain.email.repository;

import GFO.Spring.domain.email.entity.EmailVerify;
import org.springframework.data.repository.CrudRepository;

public interface EmailVerifyRepository extends CrudRepository<EmailVerify, String> {
}
