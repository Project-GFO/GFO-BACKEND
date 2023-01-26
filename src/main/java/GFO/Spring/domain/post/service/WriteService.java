package GFO.Spring.domain.post.service;

import GFO.Spring.domain.post.presentation.dto.request.WritePostRequest;

public interface WriteService {
    void execute(WritePostRequest writePostRequest);
}
