package GFO.Spring.domain.image.presentation;

import GFO.Spring.domain.image.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageController {
    private final ImageService imageService;

    @PostMapping
    public ResponseEntity<Void> createImage(@RequestParam Long postId, @RequestParam("file") List<MultipartFile> images) throws Exception {
        imageService.execute(postId, images);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
