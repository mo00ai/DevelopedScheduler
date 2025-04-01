package com.example.developedscheduler.repository;

import com.example.developedscheduler.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {


    List<Schedule> findByUsername(String username);

    default Schedule findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"존재하지 않는 id 입니다. id = "+id));
    }
}
