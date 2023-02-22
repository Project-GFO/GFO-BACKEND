package GFO.Spring.global.util;

import GFO.Spring.domain.image.entity.Attachment;
import GFO.Spring.domain.image.exception.DirectoryMakeFailException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ImageUtil {
    public void saveFile(MultipartFile multipartFile, String extensionName) throws IOException {
        File file = new File(designatePath());
        String absolutePath = new File("").getAbsolutePath() + File.separator + File.separator;

        if (!file.exists()) {
            boolean wasSuccessful = file.mkdirs();
            if (!wasSuccessful)
                throw new DirectoryMakeFailException("디렉토리 생성에 실패했습니다");
        }

            file = new File(absolutePath + designatePath() + File.separator + UUID.randomUUID() + extensionName);
            multipartFile.transferTo(file);

            file.setWritable(true);
            file.setReadable(true);

    }

    public String designatePath() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter =
                DateTimeFormatter.ofPattern("yyyyMMdd");
        String current_date = now.format(dateTimeFormatter);

        return "images" + File.separator + current_date;
    }
}
