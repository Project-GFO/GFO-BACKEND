package GFO.Spring.domain.post.service.serviceimpl;

import GFO.Spring.domain.post.entity.Post;
import GFO.Spring.domain.post.exception.EmailMismatchException;
import GFO.Spring.domain.post.exception.PostNotFoundException;
import GFO.Spring.domain.post.repository.PostRepository;
import GFO.Spring.domain.post.service.RemovePostService;
import GFO.Spring.global.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RemovePostServiceImpl implements RemovePostService {
    private final PostRepository postRepository;
    private final UserUtil userUtil;

    @Override
    public void execute(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException("게시물을 찾을 수 없습니다"));
        if((post.getUser().getEmail().equals(userUtil.currentUser().getEmail()))) {
            postRepository.deleteById(id);
        }
        else {
            throw new EmailMismatchException("이메일이 일치하지 않습니다");
        }
    }
}
