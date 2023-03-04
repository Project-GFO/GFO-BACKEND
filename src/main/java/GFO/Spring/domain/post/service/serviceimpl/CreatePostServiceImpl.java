package GFO.Spring.domain.post.service.serviceimpl;

import GFO.Spring.domain.post.entity.Post;
import GFO.Spring.domain.post.presentation.dto.request.CreatePostRequest;
import GFO.Spring.domain.post.repository.PostRepository;
import GFO.Spring.domain.post.service.CreatePostService;
import GFO.Spring.domain.user.entity.User;
import GFO.Spring.global.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreatePostServiceImpl implements CreatePostService {
    private final UserUtil userUtil;
    private final PostRepository postRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void execute(CreatePostRequest createPostRequest) {
        User user = userUtil.currentUser();

        Post post = Post.builder()
                .title(createPostRequest.getTitle())
                .content(createPostRequest.getContent())
                .user(user)
                .build();

        postRepository.save(post);
    }
}
