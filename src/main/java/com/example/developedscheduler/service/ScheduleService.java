package com.example.developedscheduler.service;

import com.example.developedscheduler.common.Const;
import com.example.developedscheduler.dto.schedule.ScheduleResponseDto;
import com.example.developedscheduler.entity.Schedule;
import com.example.developedscheduler.entity.User;
import com.example.developedscheduler.repository.ScheduleRepository;
import com.example.developedscheduler.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
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


    //스케줄 생성 서비스
    public ScheduleResponseDto save(String title, String contents, HttpSession session) {

        //사용자가 필수값 입력했는지 확인
        if (title == null ) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "일정 제목=은 필수값입니다. 재입력해주세요.");
        }

        //contents의 default값 적용
        if (contents == null) {
            contents = "내용없음";
        }

        //작성자명으로 유저 찾기
        Long userId = (Long) session.getAttribute(Const.LOGIN_USER);
        User foundUser = userRepository.findByIdOrElseThrow(userId);


        //일정 생성
        Schedule schedule = new Schedule(title, contents);
        schedule.setUser(foundUser);

        //일정 저장
        Schedule savedSchedule = scheduleRepository.save(schedule);

        //반환
        return new ScheduleResponseDto(savedSchedule.getId(), savedSchedule.getTitle(), savedSchedule.getContents(), foundUser.getName());
    }

    //유저명으로 유저리스트 조회
    public List<ScheduleResponseDto> findBy(String username) {

        return scheduleRepository.findByUserName(username).stream().map(ScheduleResponseDto::toDto).toList();
    }

    //모든 유저 조회
    public List<ScheduleResponseDto> findAll() {

        return scheduleRepository.findAll().stream().map(ScheduleResponseDto::toDto).toList();
    }

    //특정 유저 조회(id사용)
    public ScheduleResponseDto findById(Long id) {

        //조회 성공시 반환. 아닐 시 리포지터리에서 예외처리
        Schedule foundSchedule = scheduleRepository.findByIdOrElseThrow(id);

        return ScheduleResponseDto.toDto(foundSchedule);
    }

    @Transactional
    public ScheduleResponseDto updateSchedule(Long id, String title, String contents) {

        //일정이 있는지 조회
        Schedule foundSchedule = scheduleRepository.findByIdOrElseThrow(id);

        //사용자가 입력값들을 적었는지 확인
        // 안적었을시(null) 수정 전 원본의 데이터를 적용
        if (title == null) {
            title = foundSchedule.getTitle();
        }

        if (contents == null) {
            contents = foundSchedule.getContents();
        }

        //작성한 유저 확인
        User user = userRepository.findUserByNameOrElseThrow(foundSchedule.getUser().getName());

        //일정 수정
        foundSchedule.updateSchedule(title, contents);

        return ScheduleResponseDto.toDto(foundSchedule);
    }

    @Transactional
    public void deleteSchedule(Long id) {

        //아이디가 있는지 확인
        Schedule foundSchedule= scheduleRepository.findByIdOrElseThrow(id);

        //일정 삭제
        scheduleRepository.delete(foundSchedule);
    }
}
