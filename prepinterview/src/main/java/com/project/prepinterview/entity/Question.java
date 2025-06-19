package com.project.prepinterview.entity;


import com.project.prepinterview.enums.Category;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "question_id",nullable = false,updatable = false)
    private String questionId;

    @Column(name = "question",nullable = false)
    private String question;

    @Column(name = "options", nullable = false)
    private String options;

    @Column(name = "correct_answer", nullable = false)
    private String correctAnswer;

    @Column(name = "difficulty", nullable = false)
    private String difficulty;

    @Column(name = "category", nullable = false)
    private Category category;

    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;



}
