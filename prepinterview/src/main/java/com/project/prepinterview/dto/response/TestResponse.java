package com.project.prepinterview.dto.response;

import com.project.prepinterview.entity.User;

import java.time.LocalDateTime;

public record TestResponse(
        String testId,
        User userName,
        String userMarks,
        String testType
) {
}
