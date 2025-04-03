package com.example.developedscheduler.dto.schedule;

import lombok.Getter;

//일정 생성용 요청 dto
@Getter
public class SchedulePostRequestDto {

    private final String title;

    private final String contents;


    public SchedulePostRequestDto(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

}


//사용자의 요청을 서버에서 바꾸면 원치않게 에러
// setter가 열려있음 남들이 바꿀 수 있음
