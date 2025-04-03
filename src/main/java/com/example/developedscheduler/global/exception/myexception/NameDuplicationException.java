package com.example.developedscheduler.global.exception.myexception;

import com.example.developedscheduler.global.exception.CustomException;
import com.example.developedscheduler.global.exception.ErrorCode;

public class NameDuplicationException extends CustomException {
    public NameDuplicationException(ErrorCode errorCode) {
        super(errorCode);
    }

    public NameDuplicationException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
