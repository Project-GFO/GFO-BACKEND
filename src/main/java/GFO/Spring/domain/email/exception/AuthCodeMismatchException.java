package GFO.Spring.domain.email.exception;

import GFO.Spring.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class AuthCodeMismatchException extends RuntimeException {
    private final ErrorCode errorCode;

    public AuthCodeMismatchException(String message) {
        super(message);
        this.errorCode = ErrorCode.AUTH_CODE_MISMATCH;
    }
}
