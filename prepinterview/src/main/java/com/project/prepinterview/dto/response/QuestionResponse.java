package com.project.prepinterview.dto.response;

import com.project.prepinterview.enums.Category;

public record QuestionResponse(
        String questionId,
        String question,
        String options,
        String correctAnswer,
        String difficulty,
        Category category,
        Long createdAt
) {
}
