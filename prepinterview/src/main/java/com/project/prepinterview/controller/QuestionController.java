package com.project.prepinterview.controller;

import com.project.prepinterview.dto.request.QuestionRequest;
import com.project.prepinterview.dto.response.QuestionResponse;
import com.project.prepinterview.dto.wrapper.ResponseStructure;
import com.project.prepinterview.service.contract.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("questions/addquestion")
    public ResponseEntity<ResponseStructure<QuestionResponse>> addQuestion(@RequestBody QuestionRequest request){
        QuestionResponse response = questionService.addQuestion(request);
        ResponseStructure<QuestionResponse> responseStructure = new ResponseStructure<>(HttpStatus.CREATED.value(), "Question is added Succesfully",response);
        return new ResponseEntity<ResponseStructure<QuestionResponse>>(responseStructure,HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("questions/deletequestion")
    public ResponseEntity<ResponseStructure<QuestionResponse>> deleteQuestion(@RequestParam String questionId){
        QuestionResponse response = questionService.deleteQuestion(questionId);
        ResponseStructure<QuestionResponse> responseStructure = new ResponseStructure<>(HttpStatus.OK.value(), "Question is deleted Succesfully",response);
        return new ResponseEntity<ResponseStructure<QuestionResponse>>(responseStructure,HttpStatus.OK);
    }
}
