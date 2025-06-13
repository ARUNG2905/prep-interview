package com.project.prepinterview.dto.response;

import com.project.prepinterview.entity.User;

public record InterviewSessionResponse(
        String inteviewId,
        String userName,
        String interviewType,
        String userMarks,
        String status
) {
}
