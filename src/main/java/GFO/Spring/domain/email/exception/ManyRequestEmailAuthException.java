package GFO.Spring.domain.email.exception;

import GFO.Spring.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class ManyRequestEmailAuthException extends RuntimeException {
    private final ErrorCode errorCode;

    public ManyRequestEmailAuthException(String message) {
        super(message);
        this.errorCode = ErrorCode.MANY_REQUEST_EMAIL_AUTH;
    }
}
