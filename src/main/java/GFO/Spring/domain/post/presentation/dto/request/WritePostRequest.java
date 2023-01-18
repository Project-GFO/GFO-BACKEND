package GFO.Spring.domain.post.presentation.dto.request;

import GFO.Spring.domain.post.entity.Attachment;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@Builder
@RequiredArgsConstructor
public class WritePostRequest {
    private final String title;
    private final String content;

    private final List<String> contentFileURLs;
}
