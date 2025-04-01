package com.example.developedscheduler.dto;

import lombok.Getter;

@Getter
public class ScheduleUpdateRequestDto {

    private final String title;

    private final String contents;

    private final String username;

    public ScheduleUpdateRequestDto(String title, String contents, String username) {
        this.title = title;
        this.contents = contents;
        this.username = username;
    }
}

