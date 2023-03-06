package GFO.Spring.domain.comment.presentation;

import GFO.Spring.domain.comment.presentation.dto.request.CreateCommentReqDto;
import GFO.Spring.domain.comment.service.CreateCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CreateCommentService createCommentService;

    @PostMapping
    public ResponseEntity<Void> createComment(@RequestBody @Valid CreateCommentReqDto createCommentReqDto) {
        createCommentService.execute(createCommentReqDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
