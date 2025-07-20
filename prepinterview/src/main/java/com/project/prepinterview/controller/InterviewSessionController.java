package com.project.prepinterview.controller;

import com.project.prepinterview.dto.request.InterviewSessionRequest;
import com.project.prepinterview.dto.response.InterviewSessionResponse;
import com.project.prepinterview.dto.wrapper.ResponseStructure;
import com.project.prepinterview.entity.InterviewSession;
import com.project.prepinterview.service.contract.InterviewSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/interview")
public class InterviewSessionController {
@Autowired
private InterviewSessionService interviewSessionService;
@PostMapping("/schedule")
public ResponseEntity<ResponseStructure<InterviewSessionResponse>> schedule(@Validated @RequestBody InterviewSessionRequest request){

    InterviewSessionResponse interviewSessionResponse =interviewSessionService.schedule(request);
    ResponseStructure<InterviewSessionResponse> responseStructure = new ResponseStructure<>(HttpStatus.CREATED.value(), "scheduled the interview",interviewSessionResponse);
    return new ResponseEntity<>(responseStructure,HttpStatus.CREATED);
}

@GetMapping("/start")
    public ResponseEntity<ResponseStructure<String>> startInterview(@RequestParam String interviewId){
    interviewSessionService.startInterview(interviewId);
    ResponseStructure<String> responseStructure = new ResponseStructure<>(HttpStatus.OK.value(), "Interview Started",interviewId);
    return new ResponseEntity<>(responseStructure,HttpStatus.OK);
}
}
