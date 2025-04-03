package com.example.developedscheduler.domain.dto.user;

import com.example.developedscheduler.domain.entity.User;
import lombok.Getter;

//유저 응답 dto
@Getter
public class UserResponseDto {

    private final Long id;

    private final String name;

    private final String email;

    public UserResponseDto(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    //유저 엔티티 객체를 바로 Dto로 바꿀 수 있게 하는 메서드
    public static UserResponseDto toDto(User user) {
        return new UserResponseDto(user.getId(), user.getName(), user.getEmail());
    }
}
