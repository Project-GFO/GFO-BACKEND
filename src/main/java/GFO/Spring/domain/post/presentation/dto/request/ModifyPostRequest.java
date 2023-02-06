package GFO.Spring.domain.post.presentation.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import javax.validation.constraints.NotBlank;

@Getter
@RequiredArgsConstructor
public class ModifyPostRequest {
    @NotBlank(message = "수정한 제목이 비어있습니다")
    private final String title;
    @NotBlank(message = "수정한 내용이 비어있습니다")
    private final String content;
}
