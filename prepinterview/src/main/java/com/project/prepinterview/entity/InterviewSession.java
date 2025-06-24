package com.project.prepinterview.entity;

import com.project.prepinterview.enums.InterviewRole;
import com.project.prepinterview.enums.InterviewStatusRole;
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
public class InterviewSession {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "test_id", updatable = false, nullable = false)
    private String inteviewId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User candidate;

    @Enumerated(EnumType.STRING)
    @Column(name = "test_type", nullable = false, updatable = false)
    private InterviewRole interviewType;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_type", nullable = false, updatable = false)
    private InterviewStatusRole status;

    @CreationTimestamp
    @Column(name = "start_time", nullable = false, updatable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time", nullable = false,updatable = false)
    private LocalDateTime endTime;
}
