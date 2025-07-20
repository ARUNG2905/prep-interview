package com.project.prepinterview.repository;

import com.project.prepinterview.entity.InterviewSession;
import com.project.prepinterview.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InterviewSessionRepository extends JpaRepository<InterviewSession,String> {
    Optional<InterviewSession> findByInterviewId(String interviewId);
}
