package GFO.Spring.domain.comment.presentation;

import GFO.Spring.domain.comment.presentation.dto.request.CreateCommentReqDto;
import GFO.Spring.domain.comment.service.CreateCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CreateCommentService createCommentService;

    @PostMapping("/{id}")
    public ResponseEntity<Void> createComment(@PathVariable("id") Long postId, @RequestBody @Valid CreateCommentReqDto createCommentReqDto) {
        createCommentService.execute(postId, createCommentReqDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
