package GFO.Spring.domain.image.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController("/image")
public class ImageController {

    @PostMapping
    public ResponseEntity<Void> createImage(@RequestParam Long postId, @RequestParam("file") List<MultipartFile> images) throws IOException {

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
