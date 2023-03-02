package GFO.Spring.domain.image.repository;

import GFO.Spring.domain.image.entity.Image;
import org.springframework.data.repository.CrudRepository;

public interface ImageRepository extends CrudRepository<Image, Long> {
}
