package GFO.Spring.domain.image.service.impl;

import GFO.Spring.domain.image.entity.Image;
import GFO.Spring.domain.image.exception.FailedUploadImageException;
import GFO.Spring.domain.image.repository.ImageRepository;
import GFO.Spring.domain.image.service.ImageService;
import GFO.Spring.domain.post.exception.PostNotFoundException;
import GFO.Spring.domain.post.repository.PostRepository;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    @Value("${cloud.aws.s3.url}")
    private String url;
    private final AmazonS3 amazonS3;
    private final PostRepository postRepository;
    private final ImageRepository imageRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void execute(Long postId, List<MultipartFile> multipartFiles) {

        multipartFiles.forEach(image -> {
            String fileName = createFileName() + image.getOriginalFilename();
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(image.getSize());
            objectMetadata.setContentType(image.getContentType());

            try (InputStream inputStream = image.getInputStream()) {
                amazonS3.putObject(new PutObjectRequest(bucket, fileName, inputStream, objectMetadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead));
            } catch (IOException e) {
                throw new FailedUploadImageException("이미지 업로드에 실패하였습니다");
            }

            Image imageUrl = Image.builder()
                    .url(url + fileName)
                    .post(postRepository.findById(postId)
                            .orElseThrow(() -> new PostNotFoundException("해당 게시물을 찾을 수 없습니다")))
                    .build();

            imageRepository.save(imageUrl);
        });
    }

    private String createFileName() {
        return UUID.randomUUID().toString();
    }
}