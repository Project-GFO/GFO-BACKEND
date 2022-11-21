package GFO.Spring.domain.user.exception;

import GFO.Spring.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class EmailNotFoundException extends RuntimeException{
    private final ErrorCode errorCode;

    public EmailNotFoundException(String message) {
        super(message);
        this.errorCode = ErrorCode.EMAIL_NOT_FOUND;
    }
}
