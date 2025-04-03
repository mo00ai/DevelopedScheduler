package com.example.developedscheduler.controller;

import com.example.developedscheduler.common.Const;
import com.example.developedscheduler.dto.user.LoginRequestDto;
import com.example.developedscheduler.dto.user.LoginResponseDto;
import com.example.developedscheduler.dto.user.UserResponseDto;
import com.example.developedscheduler.service.AuthService;
import com.example.developedscheduler.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto dto, HttpServletRequest request) {

        LoginResponseDto responseDto = authService.login(dto.getEmail(), dto.getPassword());
        Long userId = responseDto.getId();

        HttpSession session = request.getSession();

        //회원 정보 조회
        UserResponseDto loginUser = userService.findUserById(userId);

        //Session에 로그인 회원 정보 저장
        session.setAttribute(Const.LOGIN_USER,userId);

        return new ResponseEntity<>(responseDto,HttpStatus.ACCEPTED);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if(session != null) {
            session.invalidate();
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
