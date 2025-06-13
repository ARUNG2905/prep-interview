package com.project.prepinterview.dto.request;

import com.project.prepinterview.entity.User;

public record InterviewSessionRequest(
         String userName,
         String interviewType

) {
}
