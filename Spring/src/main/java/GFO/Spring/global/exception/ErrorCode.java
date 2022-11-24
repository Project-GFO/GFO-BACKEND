package GFO.Spring.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    WRONG_PASSWORD("잘못된 비밀번호 입니다", 400),
    TOKEN_EXPIRATION("토큰이 만료 되었습니다", 401),
    TOKEN_NOT_VALID("토큰이 유효하지 않습니다", 401),
    EMAIL_NOT_FOUND("존재하지 않는 이메일 입니다", 404),
    EMAIL_ALREADY_EXIST("존재하는 이메일 입니다", 409),
    CLASS_NUMBER_ALREADY_EXIST("존재하는 학번 입니다", 409);

    private final String message;
    private final int status;

}
