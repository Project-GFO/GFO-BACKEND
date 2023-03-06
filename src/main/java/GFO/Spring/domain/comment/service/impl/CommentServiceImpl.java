package GFO.Spring.domain.comment.service.impl;

import GFO.Spring.domain.comment.entity.Comment;
import GFO.Spring.domain.comment.presentation.dto.request.CreateCommentReqDto;
import GFO.Spring.domain.comment.repository.CommentRepository;
import GFO.Spring.domain.comment.service.CommentService;
import GFO.Spring.domain.post.exception.PostNotFoundException;
import GFO.Spring.domain.post.repository.PostRepository;
import GFO.Spring.global.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserUtil userUtil;

    @Override
    public void execute(CreateCommentReqDto createCommentReqDto) {
        Comment comment = Comment.builder()
                .comment(createCommentReqDto.getComment())
                .user(userUtil.currentUser())
                .post(postRepository.findById(createCommentReqDto.getPostId()).orElseThrow(
                        () -> new PostNotFoundException("게시글을 찾을 수 없습니다")))
                .build();

        commentRepository.save(comment);
    }
}
