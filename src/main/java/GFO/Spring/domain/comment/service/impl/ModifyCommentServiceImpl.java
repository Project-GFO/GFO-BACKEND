package GFO.Spring.domain.comment.service.impl;

import GFO.Spring.domain.comment.entity.Comment;
import GFO.Spring.domain.comment.exception.CommentNotFoundException;
import GFO.Spring.domain.comment.presentation.dto.request.ModifyCommentReqDto;
import GFO.Spring.domain.comment.repository.CommentRepository;
import GFO.Spring.domain.comment.service.ModifyCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ModifyCommentServiceImpl implements ModifyCommentService {
    private final CommentRepository commentRepository;

    @Override
    public void execute(Long id, ModifyCommentReqDto modifyCommentReqDto) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new CommentNotFoundException("댓글을 찾을 수 없습니다"));
        commentRepository.save(comment.update(id, modifyCommentReqDto.getComment()));
    }
}
