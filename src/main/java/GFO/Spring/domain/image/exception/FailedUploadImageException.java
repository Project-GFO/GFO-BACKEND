package GFO.Spring.domain.image.exception;

import GFO.Spring.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class FailedUploadImageException extends RuntimeException {
    private final ErrorCode errorCode;

    public FailedUploadImageException(String message) {
        super(message);
        this.errorCode = ErrorCode.FAILED_UPLOAD_IMAGE;
    }
}
