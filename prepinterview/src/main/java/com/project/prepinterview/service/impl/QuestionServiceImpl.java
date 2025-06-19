package com.project.prepinterview.service.impl;

import com.project.prepinterview.dto.mapper.QuestionMapper;
import com.project.prepinterview.dto.request.QuestionRequest;
import com.project.prepinterview.dto.response.QuestionResponse;
import com.project.prepinterview.entity.Question;
import com.project.prepinterview.repository.QuestionRepository;
import com.project.prepinterview.service.contract.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuestionMapper questionMapper;


    @Override
    public QuestionResponse addQuestion(QuestionRequest request) {
        Question question = questionMapper.toEntity(request,new Question());
        questionRepository.save(question);
        return questionMapper.toResponse(question);
    }

    @Override
    public QuestionResponse deleteQuestion(String questionId) {
        Question question = questionRepository.findById(questionId).orElseThrow(()-> new RuntimeException("Question Not Found"));
        questionRepository.delete(question);
        return questionMapper.toResponse(question);
    }
}
