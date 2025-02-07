package com.pingo.exception;

/*
 *  ※ ExceptionCode Enum Class
 *     - Exception Code를 사전에 정의하는 Class
 *     - 모든 Custom Exception Code를 한 곳에 정의해 유지보수성 향상
 */

public enum ExceptionCode {

    // USER
    DUPLICATE_USER_NO(409, "USER_NO_DUPLICATE", "중복된 유저 아이디 입니다."),
    INVALID_USER_ID(409, "USER_ID_INVALID", "유저 아이디가 유효하지 않습니다."),
    INVALID_USER_PW(409, "INVALID_USER_PW", "유저 비밀번호가 유효하지 않습니다."),
    INVALID_USER_NAME(409, "INVALID_USER_NAME", "유저 이름이 유효하지 않습니다."),
    INVALID_USER_NICK(409, "INVALID_USER_NICK", "유저 닉네임이 유효하지 않습니다."),
    INVALID_USER_GENDER(409, "INVALID_USER_GENDER", "유저 성별이 유효하지 않습니다."),
    INVALID_USER_BIRTH(409, "INVALID_USER_BIRTH", "유저 생년월일이 유효하지 않습니다."),
    SIGN_UP_FAIL(400, "SIGN_UP_FAIL", "회원가입에 실패했습니다"),

    // FILE
    FILE_UPLOAD_FAIL(422, "FILE_UPLOAD_FAIL", "파일 업로드에 실패했습니다."),
    DUPLICATE_IMAGE_NO(409, "DUPLICATE_IMAGE_NO", "중복된 이미지입니다."),

    ;

    private final int status;
    private final String code;
    private final String message;

    ExceptionCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
