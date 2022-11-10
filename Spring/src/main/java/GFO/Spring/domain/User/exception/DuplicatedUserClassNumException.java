package GFO.Spring.domain.User.exception;

import GFO.Spring.global.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DuplicatedUserClassNumException extends RuntimeException{
    private ErrorCode errorCode;

    public DuplicatedUserClassNumException(String message) {
        super(message);
        this.errorCode = ErrorCode.CLASS_NUMBER_ALREADY_EXIST;
    }
}
