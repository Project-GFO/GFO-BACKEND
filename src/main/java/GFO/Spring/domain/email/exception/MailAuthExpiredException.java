package GFO.Spring.domain.email.exception;

import GFO.Spring.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class MailAuthExpiredException extends RuntimeException{

    private final ErrorCode errorCode;

    public MailAuthExpiredException(String message){
        super(message);
        this.errorCode = ErrorCode.CODE_EXPIRED;
    }
}
