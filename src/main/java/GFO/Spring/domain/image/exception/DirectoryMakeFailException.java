package GFO.Spring.domain.image.exception;

import GFO.Spring.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class DirectoryMakeFailException extends RuntimeException{
    private final ErrorCode errorCode;
    public DirectoryMakeFailException(String message) {
        super(message);
        this.errorCode = ErrorCode.DIRECTORY_MAKE_FAIL;
    }
}
