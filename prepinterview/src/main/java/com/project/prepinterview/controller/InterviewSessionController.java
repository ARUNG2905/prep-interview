package com.project.prepinterview.controller;

import com.project.prepinterview.dto.request.InterviewSessionRequest;
import com.project.prepinterview.dto.response.InterviewSessionResponse;
import com.project.prepinterview.dto.wrapper.ResponseStructure;
import com.project.prepinterview.entity.InterviewSession;
import com.project.prepinterview.service.contract.InterviewSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InterviewSessionController {
@Autowired
private InterviewSessionService interviewSessionService;
@PostMapping("/schedule-interview")
public ResponseEntity<ResponseStructure<InterviewSessionResponse>> schedule(@RequestBody InterviewSessionRequest request){

    InterviewSessionResponse interviewSessionResponse =interviewSessionService.schedule(request);
    ResponseStructure<InterviewSessionResponse> responseStructure = new ResponseStructure<>(HttpStatus.CREATED.value(), "scheduled the interview",interviewSessionResponse);
    return new ResponseEntity<>(HttpStatus.CREATED);
}
}
