package com.project.prepinterview.dto.request;

import com.project.prepinterview.entity.Candidate;
import com.project.prepinterview.entity.User;

public record InterviewSessionRequest(
         String Candidate,
         String interviewType

) {
}
