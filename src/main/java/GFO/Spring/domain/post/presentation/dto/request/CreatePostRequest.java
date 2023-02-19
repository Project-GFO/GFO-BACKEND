package GFO.Spring.domain.post.presentation.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Builder
@RequiredArgsConstructor
public class CreatePostRequest {
    @NotBlank(message = "제목은 필수 입력값입니다")
    private final String title;
    @NotBlank(message = "내용은 필수 입력값입니다")
    private final String content;
}
