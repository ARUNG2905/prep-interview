package com.project.prepinterview.dto.mapper;

import com.project.prepinterview.dto.request.InterviewSessionRequest;
import com.project.prepinterview.dto.response.InterviewSessionResponse;
import com.project.prepinterview.entity.InterviewSession;
import com.project.prepinterview.entity.User;
import com.project.prepinterview.enums.InterviewRole;
import com.project.prepinterview.enums.InterviewStatusRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
public class InterviewSessionMapper {


    public InterviewSession toEntity(InterviewSessionRequest request, InterviewSession session) {

        session.setInterviewType(InterviewRole.valueOf(request.interviewType().toUpperCase()));
        session.setStartTime(LocalDateTime.now());
        session.setEndTime(session.getStartTime().plusMinutes(30));
        session.setStatus(InterviewStatusRole.STARTED);


        return session;
    }


    public InterviewSessionResponse toResponse(InterviewSession session) {
        return new InterviewSessionResponse(
                session.getCandidate().toString(),
                session.getInterviewType().toString(),
                session.getStatus().toString()
        );
    }
}
