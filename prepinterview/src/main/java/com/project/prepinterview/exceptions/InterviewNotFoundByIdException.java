package com.project.prepinterview.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public class InterviewNotFoundByIdException extends RuntimeException {
    String message;
}
