package com.pingo.exception;

/*
 *  ※ ExceptionCode Enum Class
 *     - Exception Code를 사전에 정의하는 Class
 *     - 모든 Custom Exception Code를 한 곳에 정의해 유지보수성 향상
 */

public enum ExceptionCode {

    // USER
    DUPLICATE_USER_NO(409, "USER_NO_DUPLICATE", "유저 번호가 이미 존재합니다."),
    
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
