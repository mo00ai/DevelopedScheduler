package com.example.developedscheduler.filter;

import com.example.developedscheduler.common.Const;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

@Slf4j
public class LoginFilter  implements Filter {

    private static final String[] WHITE_LIST = {"/","/users/signup","/auth/login","/auth/logout"};

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;

        //요청 uri, method 정보
        String requestURI = request.getRequestURI();
        String requestMethod = request.getMethod();

        HttpServletResponse response = (HttpServletResponse) servletResponse;

        log.info("로그인 필터 로직 실행");

        //whitelist에 포함되어있지 않는 경우 로직 실행
        if (!isWhiteList(requestURI, requestMethod)) {

            HttpSession session = request.getSession(false);

            if (session == null || session.getAttribute(Const.LOGIN_USER) == null) {
                throw new RuntimeException("로그인해주세요");
            }

            //로그인 성공 로직
            log.info("로그인에 성공했습니다.");

        } else {
            if(requestMethod.equals("GET")) {
                log.info("비로그인이어도 조회는 가능합니다.");
            }

        }

        filterChain.doFilter(request, response);
    }

    //get요청은 조회
    //조회는 로그인 안해도 모든 사용자가 할 수 있도록 인가 구현함
    private boolean isWhiteList(String requestURI, String requestMethod) {

        if(requestMethod.equals("GET")) {
            return PatternMatchUtils.simpleMatch(new String[]{
                    "/schedules",
                    "/schedules/*",
                    "/users/*",
                    "/users"
            }, requestURI);
        }

        return PatternMatchUtils.simpleMatch(WHITE_LIST,requestURI);
    }
}
