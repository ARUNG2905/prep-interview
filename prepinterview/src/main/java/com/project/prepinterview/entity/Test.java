package com.project.prepinterview.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "test_id",updatable = false,nullable = false)
    private String testId;

    @Column(name ="user_name",updatable = false,nullable = false)
    @ManyToOne
    private User user;

    @Column(name = "Test_type",updatable = false,nullable = false)
    private String testType;

    @Column(name = "marks",nullable = false,updatable = false)
    private String userMarks;

    @CreationTimestamp
    @Column(name = "start_time",nullable = false,updatable = false)
    private LocalDateTime startTime;

    @CreationTimestamp
    @Column(name = "end_time",nullable = false,updatable = false)
    private LocalDateTime endTime;



    //private String avgTime;
}
