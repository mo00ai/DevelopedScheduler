package com.example.developedscheduler.dto.user;

import lombok.Getter;

//유저 요청 dto
@Getter
public class UserRequestDto {

    private final String name;

    private final String address;

    public UserRequestDto(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
