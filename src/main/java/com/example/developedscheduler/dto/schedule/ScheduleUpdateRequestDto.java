package com.example.developedscheduler.dto.schedule;

import lombok.Getter;

//일정 수정 요청 dto
@Getter
public class ScheduleUpdateRequestDto {

    private final String title;

    private final String contents;


    public ScheduleUpdateRequestDto(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}

