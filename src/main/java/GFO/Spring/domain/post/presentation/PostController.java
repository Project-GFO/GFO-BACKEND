package GFO.Spring.domain.post.presentation;

import GFO.Spring.domain.post.presentation.dto.request.CreatePostRequest;
import GFO.Spring.domain.post.service.CreatePostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final CreatePostService createPostService;

    @PostMapping
    public ResponseEntity<Void> createPost(@RequestBody @Valid CreatePostRequest createPostRequest) {
        createPostService.execute(createPostRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
