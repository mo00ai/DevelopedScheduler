package com.example.developedscheduler.domain.controller;

import com.example.developedscheduler.domain.common.AuthUtils;
import com.example.developedscheduler.domain.dto.user.UpdateUserRequestDto;
import com.example.developedscheduler.domain.dto.user.UserRequestDto;
import com.example.developedscheduler.domain.dto.user.UserResponseDto;
import com.example.developedscheduler.domain.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //회원 생성(회원가입)
    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> singUpUser(@Valid @RequestBody UserRequestDto requestDto) {

        UserResponseDto userResponseDto = userService.signUpUser(requestDto.getName(), requestDto.getEmail(), requestDto.getPassword());

        return new ResponseEntity<>(userResponseDto,HttpStatus.CREATED);
    }

    //회원 전체 조회
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAllUsers() {
        List<UserResponseDto> userResponseDtoList = userService.findAllUsers();

        return new ResponseEntity<>(userResponseDtoList,HttpStatus.OK);
    }

    //특정 회원 조회
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findUserById(@PathVariable Long id) {

        UserResponseDto userResponseDto = userService.findUserById(id);

        return new ResponseEntity<>(userResponseDto,HttpStatus.OK);
    }

    //특정 회원 수정
    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id, @Valid @RequestBody UpdateUserRequestDto requestDto, HttpSession session) {

        //세션 아이디 확인
        AuthUtils.checkSessionId(id,session);

        UserResponseDto userResponseDto = userService.updateUser(id, requestDto.getName(), requestDto.getEmail(),requestDto.getOldPassword(), requestDto.getNewPassword());

        return new ResponseEntity<>(userResponseDto,HttpStatus.OK);
    }

    //회원 탈퇴 -> 모든 게시글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id, @RequestParam String password, HttpSession session) {

        //세션 아이디 확인
        AuthUtils.checkSessionId(id,session);

        userService.deleteUser(id, password);

        return new ResponseEntity<>(HttpStatus.OK);
    }




}
