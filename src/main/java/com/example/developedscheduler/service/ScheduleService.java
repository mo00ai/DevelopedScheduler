package com.example.developedscheduler.service;

import com.example.developedscheduler.dto.ScheduleResponseDto;
import com.example.developedscheduler.entity.Schedule;
import com.example.developedscheduler.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;


    public ScheduleResponseDto save(String title, String contents, String username) {

        //사용자가 필수값 입력했는지 확인
        if(title==null||username==null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"일정 제목과 작성자명은 필수값입니다. 재입력해주세요.");
        }

        //contents의 default값 적용
        if(contents ==null) {
            contents = "내용없음";
        }

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

    public ScheduleResponseDto findById(Long id) {

        Schedule foundSchedule = scheduleRepository.findByIdOrElseThrow(id);

        return ScheduleResponseDto.toDto(foundSchedule);
    }

    @Transactional
    public ScheduleResponseDto updateSchedule(Long id, String title, String contents, String username) {

        Schedule foundSchedule = scheduleRepository.findByIdOrElseThrow(id);

        if(title == null) {
            title = foundSchedule.getTitle();
        }

        if(contents == null) {
            contents = foundSchedule.getContents();
        }

        if(username ==null) {
            username = foundSchedule.getUsername();
        }

        foundSchedule.updateSchedule(title,contents,username);

        return ScheduleResponseDto.toDto(foundSchedule);
    }

    public void deleteSchedule(Long id) {

        Schedule foundBoard = scheduleRepository.findByIdOrElseThrow(id);

        scheduleRepository.delete(foundBoard);
    }
}
