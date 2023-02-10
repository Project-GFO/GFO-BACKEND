package GFO.Spring.domain.image.repository;

import GFO.Spring.domain.image.entity.Attachment;
import org.springframework.data.repository.CrudRepository;

public interface AttachmentRepository extends CrudRepository<Attachment, Long> {
}
