package GFO.Spring.domain.email.exception;

import GFO.Spring.global.exception.ErrorCode;

public class EmailSendFailedException extends RuntimeException{
    private final ErrorCode errorCode;

    public EmailSendFailedException(String message) {
        super(message);
        this.errorCode = ErrorCode.EMAIL_SEND_FAIL;
    }
}
