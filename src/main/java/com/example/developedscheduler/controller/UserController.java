package com.example.developedscheduler.controller;

import com.example.developedscheduler.dto.user.SignUpRequestDto;
import com.example.developedscheduler.dto.user.UserResponseDto;
import com.example.developedscheduler.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> singUpUser(@RequestBody SignUpRequestDto requestDto) {

        UserResponseDto userResponseDto = userService.signUpUser(requestDto.getName(), requestDto.getAddress());

        return new ResponseEntity<>(userResponseDto,HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAllUsers() {
        List<UserResponseDto> userResponseDtoList = userService.findAllUsers();

        return new ResponseEntity<>(userResponseDtoList,HttpStatus.OK);
    }


}
