package GFO.Spring.domain.comment.service;

import GFO.Spring.domain.comment.presentation.dto.request.ModifyCommentReqDto;

public interface ModifyCommentService {
    void execute(Long id, ModifyCommentReqDto modifyCommentReqDto);
}
