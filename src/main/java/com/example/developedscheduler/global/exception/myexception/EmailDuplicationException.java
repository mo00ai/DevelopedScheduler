package com.example.developedscheduler.global.exception.myexception;

import com.example.developedscheduler.global.exception.CustomException;
import com.example.developedscheduler.global.exception.ErrorCode;

public class EmailDuplicationException extends CustomException {
    public EmailDuplicationException(ErrorCode errorCode) {
        super(errorCode);
    }

    public EmailDuplicationException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
