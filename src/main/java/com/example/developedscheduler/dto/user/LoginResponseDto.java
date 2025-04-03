package com.example.developedscheduler.dto.user;

import lombok.Getter;

@Getter
public class LoginResponseDto {

    private final Long id;

    private final String name;

    private final String email;

    public LoginResponseDto(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
