package GFO.Spring.domain.post.repository;

import GFO.Spring.domain.post.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
}
