package com.project.prepinterview.service.contract;

import com.project.prepinterview.dto.request.QuestionRequest;
import com.project.prepinterview.dto.response.QuestionResponse;

public interface QuestionService {
    QuestionResponse addQuestion(QuestionRequest request);

    QuestionResponse deleteQuestion(String questionId);
}
