package com.example.developedscheduler.domain.controller;

import com.example.developedscheduler.domain.common.AuthUtils;
import com.example.developedscheduler.domain.dto.schedule.SchedulePostRequestDto;
import com.example.developedscheduler.domain.dto.schedule.ScheduleResponseDto;
import com.example.developedscheduler.domain.dto.schedule.ScheduleUpdateRequestDto;
import com.example.developedscheduler.domain.service.ScheduleService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    //일정 생성
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> saveSchedule(@Valid @RequestBody SchedulePostRequestDto requestDto, HttpSession session) {

        ScheduleResponseDto scheduleResponseDto = scheduleService.save(requestDto.getTitle(), requestDto.getContents(), session);

        return new ResponseEntity<>(scheduleResponseDto,HttpStatus.CREATED);
    }

    //일정 유저별 조회
    @GetMapping(params = "username")
    public ResponseEntity<List<ScheduleResponseDto>> findScheduleByUser(@RequestParam String username) {

        List<ScheduleResponseDto> scheduleResponseDtoList = scheduleService.findBy(username);

        return new ResponseEntity<>(scheduleResponseDtoList,HttpStatus.OK);
    }

    //일정 전체 조회
    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAllSchedule() {

        List<ScheduleResponseDto> scheduleResponseDtoList = scheduleService.findAll();

        return new ResponseEntity<>(scheduleResponseDtoList, HttpStatus.OK);
    }

    //일정 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findScheduleById(@PathVariable Long id) {

        ScheduleResponseDto scheduleResponseDto = scheduleService.findById(id);

        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.OK);
    }

    //일정수정
    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(@PathVariable Long id, @Valid @RequestBody ScheduleUpdateRequestDto requestDto, HttpSession session) {

        AuthUtils.checkSessionId(id,session);

        ScheduleResponseDto scheduleResponseDto = scheduleService.updateSchedule(id, requestDto.getTitle(),requestDto.getContents());

        return new ResponseEntity<>(scheduleResponseDto,HttpStatus.OK);
    }

    //일정삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id, HttpSession session) {

        AuthUtils.checkSessionId(id,session);

        scheduleService.deleteSchedule(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }



}
