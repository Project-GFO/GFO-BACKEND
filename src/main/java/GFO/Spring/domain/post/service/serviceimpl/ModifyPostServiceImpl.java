package GFO.Spring.domain.post.service.serviceimpl;

import GFO.Spring.domain.post.entity.Post;
import GFO.Spring.domain.post.exception.PostNotFoundException;
import GFO.Spring.domain.post.presentation.dto.request.ModifyPostRequest;
import GFO.Spring.domain.post.repository.PostRepository;
import GFO.Spring.domain.post.service.ModifyPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ModifyPostServiceImpl implements ModifyPostService {
    private final PostRepository postRepository;

    @Override
    public void execute(Long id, ModifyPostRequest modifyPostRequest) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("게시글을 찾을 수 없습니다"));
        post.modifyPost(modifyPostRequest);
    }
}
