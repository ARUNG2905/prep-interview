package com.project.prepinterview.dto.mapper;

import com.project.prepinterview.dto.request.InterviewSessionRequest;
import com.project.prepinterview.dto.response.InterviewSessionResponse;
import com.project.prepinterview.entity.InterviewSession;
import com.project.prepinterview.entity.User;
import com.project.prepinterview.enums.InterviewRole;
import com.project.prepinterview.enums.InterviewStatusRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class InterviewSessionMapper {
    @Autowired
    InterviewSession session;

    public InterviewSession toEntity(InterviewSessionRequest request, User user) {
        InterviewSession session = new InterviewSession();
        session.setUser(user);
        session.setInterviewType(InterviewRole.valueOf(request.interviewType().toUpperCase()));
        session.setStartTime(LocalDateTime.now());
        session.setEndTime(session.getStartTime().plusMinutes(30));
        session.setStatus(InterviewStatusRole.STARTED);
        session.setUserMarks("0");

        return session;
    }


    public InterviewSessionResponse toResponse(InterviewSession session) {
        return new InterviewSessionResponse(
                session.getInteviewId(),
                session.getUser().getUserName(),
                session.getInterviewType().toString(),
                session.getUserMarks(), // Assuming this is a String
                session.getStatus().toString()
        );
    }
}
