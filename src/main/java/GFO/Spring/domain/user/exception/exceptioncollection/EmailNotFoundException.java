package GFO.Spring.domain.user.exception.exceptioncollection;

import GFO.Spring.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class EmailNotFoundException extends RuntimeException{
    private final ErrorCode errorCode;

    public EmailNotFoundException(String message) {
        super(message);
        this.errorCode = ErrorCode.EMAIL_NOT_FOUND;
    }
}
