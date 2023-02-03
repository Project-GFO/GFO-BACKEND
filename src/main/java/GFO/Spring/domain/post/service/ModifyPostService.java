package GFO.Spring.domain.post.service;

import GFO.Spring.domain.post.presentation.dto.request.ModifyPostRequest;

public interface ModifyPostService {
    void execute(Long id, ModifyPostRequest modifyPostRequest);
}
