package GFO.Spring.domain.comment.service;

import GFO.Spring.domain.comment.presentation.dto.request.CreateCommentReqDto;

public interface CommentService {
    void execute(CreateCommentReqDto createCommentReqDto);
}
