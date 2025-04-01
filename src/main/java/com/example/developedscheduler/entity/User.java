package com.example.developedscheduler.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true ,length = 20)
    private String name;

    @Column(nullable = true, length = 50)
    private String address;

    public User() {
    }

    public User(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
