package GFO.Spring.domain.email.exception;

import GFO.Spring.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class NotMatchAuthCodeException extends RuntimeException{
    private final ErrorCode errorCode;

    public NotMatchAuthCodeException(String message){
        super(message);
        this.errorCode = ErrorCode.NOTMATCH_AUTHCODE;
    }
}
