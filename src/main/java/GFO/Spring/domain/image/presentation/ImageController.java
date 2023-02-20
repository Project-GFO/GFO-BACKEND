package GFO.Spring.domain.image.presentation;

import GFO.Spring.domain.image.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageController {
    private final ImageService imageService;

    @PostMapping("/{post_id}")
    public ResponseEntity<Void> createImage(@PathVariable("post_id") Long id, @RequestPart("images") List<MultipartFile> images) throws Exception {
        imageService.execute(id, images);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
