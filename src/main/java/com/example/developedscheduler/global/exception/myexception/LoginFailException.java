package com.example.developedscheduler.global.exception.myexception;

import com.example.developedscheduler.global.exception.CustomException;
import com.example.developedscheduler.global.exception.ErrorCode;

public class LoginFailException extends CustomException {
    public LoginFailException(ErrorCode errorCode) {
        super(errorCode);
    }

    public LoginFailException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
