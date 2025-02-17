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

    // SWIPE
    DUPLICATE_SWIPE_NO(409, "SWIPE_NO_DUPLICATE", "스와이프 번호가 이미 존재합니다."),
    INVALID_SWIPE_REQUEST(400, "INVALID_SWIPE_REQUEST", "잘못된 스와이프 요청 데이터입니다."),
    MISSING_SWIPE_TYPE(400, "SWIPE_TYPE_MISSING", "스와이프 타입이 제공되지 않았습니다."),
    SWIPE_SAVE_FAILED(500, "SWIPE_SAVE_FAILED", "스와이프 저장 중 오류 발생"),
    MISSING_TARGET_USER_NO(400, "TARGET_USER_NO_MISSING", "스와이프 대상 사용자 번호가 제공되지 않았습니다."),

    // LOCATION
    MISSING_USER_NO(400, "USER_NO_MISSING", "사용자 번호가 제공되지 않았습니다."),
    MISSING_BIRTH_INFO(400, "BIRTH_INFO_MISSING", "생년월일 정보가 제공되지 않았습니다."),
    MISSING_LOCATION_INFO(400, "LOCATION_INFO_MISSING", "위치 정보가 제공되지 않았습니다."),
    LOCATION_UPDATE_FAILED(500, "LOCATION_UPDATE_FAILED", "위치 업데이트 중 오류 발생"), // Redis 또는 DB 저장 오류

    // FILE
    FILE_UPLOAD_FAIL(422, "FILE_UPLOAD_FAIL", "파일 업로드에 실패했습니다."),
    DUPLICATE_IMAGE_NO(409, "DUPLICATE_IMAGE_NO", "중복된 이미지입니다."),

    // COMMUNITY
    DUPLICATE_PLACE_REVIEW_NO(409, "DUPLICATE_PLACE_REVIEW_NO", "중복된 리뷰 번호 입니다."),
    DUPLICATE_REVIEW_IMAGE_NO(409, "DUPLICATE_REVIEW_IMAGE_NO", "중복된 리뷰 이미지 번호 입니다."),

  // MATCHING
    DUPLICATE_MATCH_NO(409, "MATCH_NO_DUPLICATE", "매칭 번호가 이미 존재합니다."),
    MATCHING_FAILED(400, "MATCHING_FAILED", "매칭서비스가 실패되었습니다.");
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
