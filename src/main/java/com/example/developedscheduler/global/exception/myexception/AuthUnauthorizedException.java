package com.example.developedscheduler.global.exception.myexception;

import com.example.developedscheduler.global.exception.CustomException;
import com.example.developedscheduler.global.exception.ErrorCode;

public class AuthUnauthorizedException extends CustomException {

    public AuthUnauthorizedException(ErrorCode errorCode) {
        super(errorCode);
    }

    public AuthUnauthorizedException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
