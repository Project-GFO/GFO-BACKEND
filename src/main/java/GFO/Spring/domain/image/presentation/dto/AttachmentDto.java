package GFO.Spring.domain.image.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AttachmentDto {
    private String originFileName;
    private String filePath;
    private Long fileSize;
}
