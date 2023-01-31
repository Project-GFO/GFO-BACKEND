package GFO.Spring.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    AUTH_CODE_MISMATCH("인증번호가 일치하지 않습니다", 400),
    WRONG_PASSWORD("잘못된 비밀번호 입니다", 400),
    TOKEN_EXPIRATION("토큰이 만료 되었습니다", 401),
    TOKEN_NOT_VALID("토큰이 유효하지 않습니다", 401),
    USER_NOT_FOUND("유저를 찾을 수 없습니다", 404),
    REFRESH_TOKEN_NOT_FOUND("리프레시 토큰을 찾을 수 없습니다", 404),
    EMAIL_NOT_FOUND("존재하지 않는 이메일 입니다", 404),
    BLACK_LIST_ALREADY_EXIST("블랙리스트에 이미 존재합니다", 409),
    EMAIL_ALREADY_EXIST("존재하는 이메일 입니다", 409),
    CLASS_NUMBER_ALREADY_EXIST("존재하는 학번 입니다", 409),
    MANY_REQUEST_EMAIL_AUTH("이메일은 15분에 최대 3회까지만 발송됩니다", 429),
    EMAIL_SEND_FAIL("메일 발송에 실패했습니다", 500);

    private final String message;
    private final int status;

}
