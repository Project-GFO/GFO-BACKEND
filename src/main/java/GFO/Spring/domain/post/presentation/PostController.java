package GFO.Spring.domain.post.presentation;

import GFO.Spring.domain.post.presentation.dto.request.CreatePostRequest;
import GFO.Spring.domain.post.presentation.dto.request.ModifyPostRequest;
import GFO.Spring.domain.post.service.CreatePostService;
import GFO.Spring.domain.post.service.ModifyPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final CreatePostService createPostService;
    private final ModifyPostService modifyPostService;

    @PostMapping
    public ResponseEntity<Void> createPost(@RequestBody @Valid CreatePostRequest createPostRequest) {
        createPostService.execute(createPostRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> modifyPost(@PathVariable Long id,  @RequestBody @Valid ModifyPostRequest modifyPostRequest) {
        modifyPostService.execute(id, modifyPostRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
