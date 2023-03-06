package GFO.Spring.domain.comment.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateCommentReqDto {
    private Long postId;
    private String comment;
}
