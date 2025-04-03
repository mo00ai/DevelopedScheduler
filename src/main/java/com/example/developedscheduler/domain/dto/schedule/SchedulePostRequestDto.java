package com.example.developedscheduler.domain.dto.schedule;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

//일정 생성용 요청 dto
@Getter
@AllArgsConstructor
public class SchedulePostRequestDto {

    @NotBlank(message = "일정 제목은 필수 입력 항목입니다.")
    @Size(max=20, message = "일정 제목은 20글자 이내입니다.")
    private final String title;

    @Size(max=100, message = "일정 내용은 100자 이내로 작성해주세요.")
    private final String contents;

}


//사용자의 요청을 서버에서 바꾸면 원치않게 에러
// setter가 열려있음 남들이 바꿀 수 있음
