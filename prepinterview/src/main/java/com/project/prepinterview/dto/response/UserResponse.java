package com.project.prepinterview.dto.response;

import java.util.List;

public record UserResponse(
        String userId,
        String userName,
        String email,
        String password


) {
}
