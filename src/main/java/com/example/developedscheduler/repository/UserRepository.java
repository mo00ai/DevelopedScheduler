package com.example.developedscheduler.repository;

import com.example.developedscheduler.entity.User;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Id> {


}
