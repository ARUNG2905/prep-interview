package com.project.prepinterview.entity;

import com.project.prepinterview.enums.InterviewRole;
import com.project.prepinterview.enums.InterviewStatusRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.web.bind.support.SessionStatus;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class InterviewSession{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "test_id",updatable = false,nullable = false)
    private String inteviewId;

//    @Column(name ="user_name",updatable = false,nullable = false)
//    @ManyToOne
//    private User user;

    @Column(name = "Test_type",updatable = false,nullable = false)
    @Enumerated(EnumType.STRING)
    private InterviewRole interviewType;

    @Column(name = "marks",nullable = false,updatable = false)
    private String userMarks;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_type",updatable = false,nullable = false)
    private InterviewStatusRole status;

    @CreationTimestamp
    @Column(name = "start_time",nullable = false,updatable = false)
    private LocalDateTime startTime;

    @CreationTimestamp
    @Column(name = "end_time",nullable = false,updatable = false)
    private LocalDateTime endTime;
}