package GFO.Spring.domain.post.repository;

import GFO.Spring.domain.post.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AttachmentRepository extends CrudRepository<Attachment, Long> {
}
