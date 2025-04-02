package com.example.developedscheduler.entity;

import jakarta.persistence.*;
import lombok.Getter;


//스케줄 엔티티
@Getter
@Entity
@Table(name="schedule")
//@DynamicInsert
public class Schedule extends ScheduleBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = true, columnDefinition = "VARCHAR(255) DEFAULT '내용 없음'")
//    @Column(nullable = true, columnDefinition = "longtext")
//    @ColumnDefault("내용 없음")
//    private String contents = "내용 없음";
    private String contents;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Schedule() {
    }


    public Schedule(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public void updateSchedule(String title, String contents) {

        this.title = title;
        this.contents = contents;
    }

    //스케줄에 User를 들록해줄 때 쓰는 setter
    public void setUser(User user) {
        this.user = user;
    }
}
