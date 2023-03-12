package GFO.Spring.domain.comment.service;

import GFO.Spring.domain.comment.presentation.dto.request.CreateCommentReqDto;

public interface CreateCommentService {
    void execute(Long postId, CreateCommentReqDto createCommentReqDto);
}
