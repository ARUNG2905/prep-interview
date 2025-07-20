package com.project.prepinterview.dto.response;

import com.project.prepinterview.entity.Candidate;
import com.project.prepinterview.entity.User;

public record InterviewSessionResponse(

        String candidateEmail,
        String interviewType,

        String status
) {
}
