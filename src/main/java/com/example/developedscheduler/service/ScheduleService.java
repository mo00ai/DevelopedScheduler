package com.example.developedscheduler.service;

import com.example.developedscheduler.dto.schedule.ScheduleResponseDto;
import com.example.developedscheduler.entity.Schedule;
import com.example.developedscheduler.entity.User;
import com.example.developedscheduler.repository.ScheduleRepository;
import com.example.developedscheduler.repository.UserRepository;
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
    private final UserRepository userRepository;


    public ScheduleResponseDto save(String title, String contents, String username) {

        //사용자가 필수값 입력했는지 확인
        if (title == null || username == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "일정 제목과 작성자명은 필수값입니다. 재입력해주세요.");
        }

        //contents의 default값 적용
        if (contents == null) {
            contents = "내용없음";
        }

        User foundUser = userRepository.findUserByNameOrElseThrow(username);

        Schedule schedule = new Schedule(title, contents);
        schedule.setUser(foundUser);

        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new ScheduleResponseDto(savedSchedule.getId(), savedSchedule.getTitle(), savedSchedule.getContents(), foundUser.getName());
    }

    public List<ScheduleResponseDto> findBy(String username) {

        return scheduleRepository.findByUserName(username).stream().map(ScheduleResponseDto::toDto).toList();
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

        if (title == null) {
            title = foundSchedule.getTitle();
        }

        if (contents == null) {
            contents = foundSchedule.getContents();
        }

        if (username == null) {
            username = foundSchedule.getUser().getName();
        }

        foundSchedule.updateSchedule(title, contents, username);

        return ScheduleResponseDto.toDto(foundSchedule);
    }

    public void deleteSchedule(Long id) {

        Schedule foundBoard = scheduleRepository.findByIdOrElseThrow(id);

        scheduleRepository.delete(foundBoard);
    }
}
