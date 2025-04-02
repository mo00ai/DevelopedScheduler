package com.example.developedscheduler.dto.user;

import lombok.Getter;

@Getter
public class SignUpRequestDto {

    private final String name;

    private final String address;

    public SignUpRequestDto(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
