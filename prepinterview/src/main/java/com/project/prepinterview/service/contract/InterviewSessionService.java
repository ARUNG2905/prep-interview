package com.project.prepinterview.service.contract;

import com.project.prepinterview.dto.request.InterviewSessionRequest;
import com.project.prepinterview.dto.response.InterviewSessionResponse;

public interface InterviewSessionService {
    InterviewSessionResponse schedule(InterviewSessionRequest request);
}