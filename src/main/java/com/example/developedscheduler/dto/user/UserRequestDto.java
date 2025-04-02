package com.example.developedscheduler.dto.user;

import lombok.Getter;

//유저 요청 dto
@Getter
public class UserRequestDto {

    private final String name;

    private final String email;

    private final String password;

    public UserRequestDto(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
