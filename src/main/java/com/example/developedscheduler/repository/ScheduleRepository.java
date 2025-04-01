package com.example.developedscheduler.repository;

import com.example.developedscheduler.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {


    List<Schedule> findByUsername(String username);
}
