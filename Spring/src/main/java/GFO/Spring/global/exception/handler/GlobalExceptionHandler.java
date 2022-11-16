package GFO.Spring.global.exception.handler;

import GFO.Spring.domain.user.exception.DuplicatedUserClassNumException;
import GFO.Spring.domain.user.exception.DuplicatedUserEmailException;
import GFO.Spring.global.exception.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DuplicatedUserClassNumException.class)
    public ResponseEntity<ErrorResponse> DuplicatedUserClassNum(DuplicatedUserClassNumException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getErrorCode().getMessage(), exception.getErrorCode().getStatus());
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.valueOf(exception.getErrorCode().getStatus()));
    }

    @ExceptionHandler(DuplicatedUserEmailException.class)
    public ResponseEntity<ErrorResponse> DuplicatedUserEmail(DuplicatedUserEmailException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getErrorCode().getMessage(), exception.getErrorCode().getStatus());
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.valueOf(exception.getErrorCode().getStatus()));
    }
}
