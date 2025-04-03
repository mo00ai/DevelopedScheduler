package com.example.developedscheduler.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "user")
public class User extends ScheduleBaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true ,length = 20)
    private String name;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 15)
    private String password;

    public User() {
    }

    public User(String name, String email,String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    //User 수정 시 사용하는 세터
    public void updateUser(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

}
