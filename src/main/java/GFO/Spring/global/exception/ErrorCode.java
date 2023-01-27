package GFO.Spring.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    NOTMATCH_AUTHCODE("인증번호가 일치하지 않습니다", 409),
    CODE_EXPIRED("인증번호가 만료되었습니다",409),
    EMAIL_ALREADY_EXIST("존재하는 이메일 입니다", 409),
    CLASS_NUMBER_ALREADY_EXIST("존재하는 학번 입니다", 409);

    private final String message;
    private final int status;
}
