package GFO.Spring.domain.comment.service.impl;

import GFO.Spring.domain.comment.entity.Comment;
import GFO.Spring.domain.comment.presentation.dto.request.CreateCommentReqDto;
import GFO.Spring.domain.comment.repository.CommentRepository;
import GFO.Spring.domain.comment.service.CreateCommentService;
import GFO.Spring.domain.post.exception.PostNotFoundException;
import GFO.Spring.domain.post.repository.PostRepository;
import GFO.Spring.global.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCommentServiceImpl implements CreateCommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserUtil userUtil;

    @Override
    public void execute(Long postId, CreateCommentReqDto createCommentReqDto) {
        Comment comment = Comment.builder()
                .comment(createCommentReqDto.getComment())
                .user(userUtil.currentUser())
                .post(postRepository.findById(postId).orElseThrow(
                        () -> new PostNotFoundException("게시글을 찾을 수 없습니다")))
                .build();

        commentRepository.save(comment);
    }
}
