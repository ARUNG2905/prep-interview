package com.project.prepinterview.repository;

import com.project.prepinterview.entity.InterviewSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterviewSessionRepository extends JpaRepository<InterviewSession,String> {
}
