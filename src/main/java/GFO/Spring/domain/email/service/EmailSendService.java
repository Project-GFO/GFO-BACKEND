package GFO.Spring.domain.email.service;

import GFO.Spring.domain.email.presentation.dto.request.EmailSendRequest;

public interface EmailSendService {
    void execute(EmailSendRequest emailSendRequest);
}
