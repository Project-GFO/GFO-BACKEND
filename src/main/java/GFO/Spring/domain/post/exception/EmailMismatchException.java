package GFO.Spring.domain.post.exception;

import GFO.Spring.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class EmailMismatchException extends RuntimeException {
    private final ErrorCode errorCode;

    public EmailMismatchException(String message) {
        super(message);
        this.errorCode = ErrorCode.EMAIL_MISMATCH;
    }
}
