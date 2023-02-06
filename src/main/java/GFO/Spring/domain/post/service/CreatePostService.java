package GFO.Spring.domain.post.service;

import GFO.Spring.domain.post.presentation.dto.request.CreatePostRequest;

public interface CreatePostService {
    void execute(CreatePostRequest createPostRequest);
}
