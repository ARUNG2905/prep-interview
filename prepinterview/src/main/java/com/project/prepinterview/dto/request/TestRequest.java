package com.project.prepinterview.dto.request;

import com.project.prepinterview.entity.User;

import java.time.LocalDateTime;

public record TestRequest(
         User userName,
         String testType

) {
}
