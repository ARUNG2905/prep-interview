package com.project.prepinterview.dto.request;

import com.project.prepinterview.enums.Category;

public record QuestionRequest(
        String question,
        String options,
        String correctAnswer,
        String difficulty,
        Category category
) {
}
