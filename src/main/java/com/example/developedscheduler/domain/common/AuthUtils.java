package com.example.developedscheduler.domain.common;

import com.example.developedscheduler.global.exception.CustomException;
import com.example.developedscheduler.global.exception.ErrorCode;
import jakarta.servlet.http.HttpSession;

public class AuthUtils {

    //세션 검증하는 메서드
    //본인만 유저 정보 수정 삭제 가능하도록
    public static void checkSessionId(Long id, HttpSession session) {
        Long sessionId = (Long) session.getAttribute(Const.LOGIN_USER);

        if(!sessionId.equals(id)) {
            throw new CustomException(ErrorCode.AUTH_FORBIDDEN);
        }
    }
}
