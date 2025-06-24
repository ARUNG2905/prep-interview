package com.project.prepinterview.dto.response;

import com.project.prepinterview.entity.Candidate;
import com.project.prepinterview.entity.User;

public record InterviewSessionResponse(

        String Candidate,
        String interviewType,

        String status
) {
}
