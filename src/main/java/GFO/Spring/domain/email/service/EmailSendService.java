package GFO.Spring.domain.email.service;

import GFO.Spring.domain.email.presentation.dto.request.EmailSendDto;

public interface EmailSendService {

    void execute(EmailSendDto emailSendDto);
}
