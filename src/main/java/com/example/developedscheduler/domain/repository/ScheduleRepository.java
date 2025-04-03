package com.example.developedscheduler.domain.repository;

import com.example.developedscheduler.domain.entity.Schedule;
import com.example.developedscheduler.domain.entity.User;
import com.example.developedscheduler.global.exception.CustomException;
import com.example.developedscheduler.global.exception.ErrorCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    //where username = username 쿼리
    List<Schedule> findByUserName(String username);

    //존재하는 id면 해당 일정 반환, 아닐시엔 예외 처리
    default Schedule findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(()-> new CustomException(ErrorCode.SCHEDULE_NOT_FOUND));
    }

    //User별 일정 조회 리스트
    List<Schedule> findAllByUser(User user);

}
