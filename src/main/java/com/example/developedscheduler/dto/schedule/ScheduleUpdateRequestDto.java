package com.example.developedscheduler.dto.schedule;

import lombok.Getter;

//일정 수정 요청 dto
@Getter
public class ScheduleUpdateRequestDto {

    private final String title;

    private final String contents;

    private final String password;

    public ScheduleUpdateRequestDto(String title, String contents, String password) {
        this.title = title;
        this.contents = contents;
        this.password = password;
    }
}

