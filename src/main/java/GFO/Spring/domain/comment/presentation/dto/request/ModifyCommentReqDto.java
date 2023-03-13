package GFO.Spring.domain.comment.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ModifyCommentReqDto {
    @NotBlank(message = "수정된 댓글은 공백일 수 없습니다")
    private String comment;
}
