package GFO.Spring.domain.image.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    void execute(Long postId, List<MultipartFile> image) throws Exception;
}
