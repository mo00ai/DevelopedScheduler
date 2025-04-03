package com.example.developedscheduler.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // Common
    INVALID_INPUT_VALUE(400, "Bad Request", "C001", "Invalid Input Value"),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed", "C002", "Method Not Allowed"),
    ENTITY_NOT_FOUND(400, "Bad Request", "C003", "Entity Not Found"),
    INTERNAL_SERVER_ERROR(500, "Server Error", "C004", "Internal Server Error"),
    INVALID_TYPE_VALUE(400, "Bad Request", "C005", "Invalid Type Value"),

    //Auth
    AUTH_UNAUTHORIZED(401,"Unauthorized","A001","본인 정보만 수정/삭제할 수 있습니다."),
    LOGIN_FAIL(401,"Unauthorized","A002","로그인 실패했습니다. 이메일/패스워드를 확인해주세요."),
    AUTH_FORBIDDEN(403,"Forbidden","A003","로그인 해주세요."),

    //User
    USER_NOT_FOUND(404, "Not Found", "U001", "요청하신 유저를 찾을 수 없습니다."),
    NAME_DUPLICATION(400,"Bad Request","U002","이미 존재하는 이름입니다."),
    EMAIL_DUPLICATION(400,"Bad Request","U003","이미 존재하는 이메일입니다."),
    PASSWORD_INCORRECT(400,"Bad Request","U004","비밀번호가 틀렸습니다. 재입력해주세요"),

    //schedule
    SCHEDULE_NOT_FOUND(404, "Not Found", "U001", "요청하신 일정을 찾을 수 없습니다.");

    private final int status;
    private final String error;
    private final String code;
    private final String message;
}
