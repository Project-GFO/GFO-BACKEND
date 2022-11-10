package GFO.Spring.domain.User.exception;


import GFO.Spring.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class DuplicatedUserEmailException extends RuntimeException{
    private ErrorCode errorCode;

    public DuplicatedUserEmailException(String message) {
        super(message);
        this.errorCode = ErrorCode.EMAIL_ALREADY_EXIST;

    }
}
