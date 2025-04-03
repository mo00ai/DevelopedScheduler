package com.example.developedscheduler.common;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class SessionUtils {

    //세션 검증하는 메서드
    //본인만 유저 정보 수정 삭제 가능하도록
    public static void checkSessionId(Long id, HttpSession session) {
        Long sessionId = (Long) session.getAttribute(Const.LOGIN_USER);

        if(!sessionId.equals(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"유저 본인만 유저 정보 수정/삭제 가능합니다.");
        }
    }
}
