package com.example.developedscheduler.repository;

import com.example.developedscheduler.entity.Schedule;
import com.example.developedscheduler.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    //where username = username 쿼리
    List<Schedule> findByUserName(String username);

    //존재하는 id면 해당 일정 반환, 아닐시엔 예외 처리
    default Schedule findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"존재하지 않는 id 입니다. id = "+id));
    }

    //User별 일정 조회 리스트
    List<Schedule> findAllByUser(User user);

    //User_id에 해당하는 모든 스케줄 삭제
    void deleteSchedulesByUser_Id(Long userId);

}
