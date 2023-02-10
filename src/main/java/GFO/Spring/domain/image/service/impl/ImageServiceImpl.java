package GFO.Spring.domain.image.service.impl;

import GFO.Spring.domain.image.entity.Attachment;
import GFO.Spring.domain.image.exception.DirectoryMakeFailException;
import GFO.Spring.domain.image.repository.AttachmentRepository;
import GFO.Spring.domain.image.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final AttachmentRepository attachmentRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void execute(List<MultipartFile> images) throws Exception {
        List<Attachment> attachments = handler(images);
        attachmentRepository.saveAll(attachments);
    }

    private List<Attachment> handler(List<MultipartFile> images) throws Exception {
        List<Attachment> attachments = new ArrayList<>();
        String absolutePath = new File("").getAbsolutePath() + File.separator + File.separator;

        File file = new File(designatePath());

        if (!file.exists()) {
            boolean wasSuccessful = file.mkdirs();
            if (!wasSuccessful)
                throw new DirectoryMakeFailException("디렉토리 생성에 실패했습니다");

        }

        List<MultipartFile> image = images.stream()
                .filter(i -> Objects.requireNonNull(i.getContentType()).endsWith("image/jpeg") || i.getContentType().endsWith("image/png"))
                .collect(Collectors.toList());

        for (MultipartFile multipartFile : image) {
            String extensionName = null;
            String contentType = multipartFile.getContentType();

            if (!ObjectUtils.isEmpty(contentType)) {
                if (contentType.endsWith("image/jpeg")) {
                    extensionName = ".jpg";
                } else if (contentType.endsWith("image/png")) {
                    extensionName = ".png";
                }
            }

            Attachment attachment = Attachment.builder()
                    .originFileName(multipartFile.getOriginalFilename())
                    .filePath(designatePath() + File.separator + UUID.randomUUID() + extensionName)
                    .fileSize(multipartFile.getSize())
                    .build();

            attachments.add(attachment);

            file = new File(absolutePath + designatePath() + File.separator + UUID.randomUUID() + extensionName);
            multipartFile.transferTo(file);

            file.setWritable(true);
            file.setReadable(true);
        }
        return attachments;
    }

    private String designatePath() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter =
                DateTimeFormatter.ofPattern("yyyyMMdd");
        String current_date = now.format(dateTimeFormatter);

        return "images" + File.separator + current_date;
    }

}