package GFO.Spring.domain.post.service.serviceimpl;

import GFO.Spring.domain.post.entity.Attachment;
import GFO.Spring.domain.post.entity.Post;
import GFO.Spring.domain.post.presentation.dto.request.WritePostRequest;
import GFO.Spring.domain.post.repository.AttachmentRepository;
import GFO.Spring.domain.post.repository.PostRepository;
import GFO.Spring.domain.post.service.WriteService;
import GFO.Spring.domain.user.entity.User;
import GFO.Spring.global.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WriteServiceImpl implements WriteService {
    private final UserUtil userUtil;
    private final PostRepository postRepository;
    private final AttachmentRepository attachmentRepository;

    @Override
    public void execute(WritePostRequest writePostRequest) {
        User user = userUtil.currentUser();

        Post post = Post.builder()
                .title(writePostRequest.getTitle())
                .content(writePostRequest.getContent())
                .user(user)
                .build();

        List<Attachment> attachments = writePostRequest.getContentFileURLs().stream()
                .map(att -> new Attachment(att ,post))
                .collect(Collectors.toList());

        postRepository.save(post);
        attachmentRepository.saveAll(attachments);
    }
}
