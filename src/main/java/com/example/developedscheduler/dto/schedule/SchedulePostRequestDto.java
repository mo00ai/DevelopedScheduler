package com.example.developedscheduler.dto.schedule;

import lombok.Getter;

@Getter
public class SchedulePostRequestDto {

    private final String title;

    private final String contents;

    private final String username;

    public SchedulePostRequestDto(String title, String contents, String username) {
        this.title = title;
        this.contents = contents;
        this.username = username;
    }

}


//사용자의 요청을 서버에서 바꾸면 원치않게 에러
// setter가 열려있음 남들이 바꿀 수 있음
