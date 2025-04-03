package com.example.developedscheduler.domain.dto.schedule;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

//일정 수정 요청 dto
@Getter
@AllArgsConstructor
public class ScheduleUpdateRequestDto {

    @Size(max=20, message = "일정 제목은 20글자 이내입니다.")
    private final String title;

    @Size(max=100, message = "일정 내용은 100자 이내로 작성해주세요.")
    private final String contents;

}

