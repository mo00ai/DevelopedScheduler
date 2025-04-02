package com.example.developedscheduler.dto.user;

import com.example.developedscheduler.entity.User;
import lombok.Getter;

//유저 응답 dto
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

    //유저 엔티티 객체를 바로 Dto로 바꿀 수 있게 하는 메서드
    public static UserResponseDto toDto(User user) {
        return new UserResponseDto(user.getId(), user.getName(), user.getAddress());
    }
}
