package com.example.developedscheduler.dto.user;

import lombok.Getter;

@Getter
public class UpdateUserRequestDto {

    private final String name;

    private final String email;

    private final String oldPassword;

    private final String newPassword;

    public UpdateUserRequestDto(String name, String email, String oldPassword, String newPassword) {
        this.name = name;
        this.email = email;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }
}
