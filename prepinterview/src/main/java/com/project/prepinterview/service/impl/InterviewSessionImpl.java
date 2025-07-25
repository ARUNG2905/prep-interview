package com.project.prepinterview.service.impl;

import com.project.prepinterview.dto.mapper.InterviewSessionMapper;
import com.project.prepinterview.dto.request.InterviewSessionRequest;
import com.project.prepinterview.dto.response.InterviewSessionResponse;
import com.project.prepinterview.entity.InterviewSession;
import com.project.prepinterview.entity.User;
import com.project.prepinterview.enums.InterviewRole;
import com.project.prepinterview.enums.InterviewStatusRole;
import com.project.prepinterview.exceptions.InterviewNotFoundByIdException;
import com.project.prepinterview.exceptions.UserNotFoundByEmailException;
import com.project.prepinterview.repository.InterviewSessionRepository;
import com.project.prepinterview.repository.UserRepository;
import com.project.prepinterview.service.contract.InterviewSessionService;
import jdk.jfr.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class InterviewSessionImpl implements InterviewSessionService {
    
    @Autowired
    private InterviewSessionMapper interviewSessionMapper;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private InterviewSessionRepository interviewSessionRepository;
    
    @Override
    public InterviewSessionResponse schedule(InterviewSessionRequest request) {
       Optional<User> candidate = userRepository.findByEmail(request.candidateEmail());

       if(candidate.isEmpty()){
           throw new UserNotFoundByEmailException("User not Found");
       }

        InterviewSession interviewSession = interviewSessionMapper.toEntity(request, new InterviewSession());
       interviewSession.setCandidate(candidate.get());
       interviewSession.setStatus(InterviewStatusRole.STARTED);
       interviewSession.setStartTime(LocalDateTime.now());
       if(interviewSession.getInterviewType()==InterviewRole.APTITUDE ||interviewSession.getInterviewType()==InterviewRole.TECHNICAL ||interviewSession.getInterviewType()==InterviewRole.CODING  ) {
           interviewSession.setEndTime(interviewSession.getStartTime().plusMinutes(30));
       }
        if (interviewSession.getInterviewType() == InterviewRole.MOCK) {
            interviewSession.setEndTime(interviewSession.getStartTime().plusMinutes(90));
        }

        interviewSessionRepository.save(interviewSession);

        return interviewSessionMapper.toResponse(interviewSession);
    }

    @Override
    public void startInterview(String interviewId) {
    InterviewSession interviewSession = interviewSessionRepository.findByInterviewId(interviewId)
            .orElseThrow(() -> new InterviewNotFoundByIdException("Interview Id not found"));

    if(interviewSession.getStatus()==InterviewStatusRole.STARTED){
        return;
    }
    interviewSession.setStatus(InterviewStatusRole.STARTED);
    interviewSessionRepository.save(interviewSession);
    }
}
