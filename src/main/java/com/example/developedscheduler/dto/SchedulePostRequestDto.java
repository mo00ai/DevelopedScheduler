package com.example.developedscheduler.dto;

import lombok.Getter;

@Getter
public class SchedulePostRequestDto {

    private final String title;

    private final String contents;

    private final String username;

//    public SchedulePostRequestDto(String title, String username) {
//        this.title = title;
//        this.username = username;
//    }

    public SchedulePostRequestDto(String title, String contents, String username) {
        this.title = title;
        this.contents = contents;
        this.username = username;
    }
}
