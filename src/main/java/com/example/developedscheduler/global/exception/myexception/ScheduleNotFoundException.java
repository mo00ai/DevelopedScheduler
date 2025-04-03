package com.example.developedscheduler.global.exception.myexception;

import com.example.developedscheduler.global.exception.CustomException;
import com.example.developedscheduler.global.exception.ErrorCode;

public class ScheduleNotFoundException extends CustomException {
    public ScheduleNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }

    public ScheduleNotFoundException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
