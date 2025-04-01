package com.example.developedscheduler.service;

import com.example.developedscheduler.dto.ScheduleResponseDto;
import com.example.developedscheduler.entity.Schedule;
import com.example.developedscheduler.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;


    public ScheduleResponseDto save(String title, String contents, String username) {

        Schedule schedule = new Schedule(title, contents, username);

        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new ScheduleResponseDto(savedSchedule.getId(),savedSchedule.getTitle(),savedSchedule.getContents(),savedSchedule.getUsername());
    }

    public List<ScheduleResponseDto> findBy(String username) {

        return scheduleRepository.findByUsername(username).stream().map(ScheduleResponseDto::toDto).toList();
    }

    public List<ScheduleResponseDto> findAll() {

        return scheduleRepository.findAll().stream().map(ScheduleResponseDto::toDto).toList();
    }
}
