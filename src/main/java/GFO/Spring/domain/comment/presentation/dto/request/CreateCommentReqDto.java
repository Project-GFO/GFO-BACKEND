package GFO.Spring.domain.comment.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class CreateCommentReqDto {
    @NotBlank(message = "댓글은 공백을 허용하지 않습니다")
    private String comment;
}
