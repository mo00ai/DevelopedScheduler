package com.example.developedscheduler.dto.user;

import com.example.developedscheduler.entity.User;
import lombok.Getter;

@Getter
public class UserResponseDto {

    private final Long id;

    private final String name;

    private final String address;

    public UserResponseDto(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public static UserResponseDto toDto(User user) {
        return new UserResponseDto(user.getId(), user.getName(), user.getAddress());
    }
}
