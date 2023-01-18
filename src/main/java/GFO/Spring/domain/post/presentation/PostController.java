package GFO.Spring.domain.post.presentation;

import GFO.Spring.domain.post.presentation.dto.request.WritePostRequest;
import GFO.Spring.domain.post.service.WriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("post")
@RequiredArgsConstructor
public class PostController {

    private final WriteService writeService;

    @PostMapping
    public ResponseEntity<Void> writePost(@RequestBody @Valid WritePostRequest writePostRequest) {
        writeService.execute(writePostRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
