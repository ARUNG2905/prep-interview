package com.project.prepinterview.dto.request;

import java.util.List;

public record UserRequest(
        String userName,
        String email,
        String password
) {
}
