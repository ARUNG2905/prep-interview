package com.project.prepinterview.controller;

import com.project.prepinterview.dto.request.UserRequest;
import com.project.prepinterview.dto.response.UserResponse;
import com.project.prepinterview.dto.wrapper.ResponseStructure;
import com.project.prepinterview.entity.User;
import com.project.prepinterview.service.contract.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
   private UserService userService;
    @PostMapping("/users/registercandidate")
    public ResponseEntity<ResponseStructure<UserResponse>> registerCandidate(@RequestBody UserRequest request){
        UserResponse userResponse = userService.registerCandidate(request);
        ResponseStructure<UserResponse> responseStructure = new ResponseStructure<>(HttpStatus.CREATED.value(), "Candidate Registered Succesfully", userResponse);
        return new ResponseEntity<ResponseStructure<UserResponse>>(responseStructure,HttpStatus.CREATED);
    }

    @PostMapping("users/registeradmin")
    public ResponseEntity<ResponseStructure<UserResponse>> registerAdmin(@RequestBody UserRequest request){
        UserResponse userResponse = userService.registerAdmin(request);
        ResponseStructure<UserResponse> responseStructure = new ResponseStructure<>(HttpStatus.CREATED.value(), "Admin is Register",userResponse);
        return new ResponseEntity<ResponseStructure<UserResponse>>(responseStructure,HttpStatus.CREATED);
  }
  @PutMapping("user/changeusername")
    public ResponseEntity<String> updateUsername(@RequestParam String newUsername){
       userService.updateUsername(newUsername);
        return new ResponseEntity<String>("Username changed Succesfully",HttpStatus.OK);


  }
    @PutMapping("user/changepassword")
    public ResponseEntity<String> updatePassword(@RequestParam String newPassword){
        userService.updatePassword(newPassword);
        return new ResponseEntity<String>("UserPaswword changed Succesfully",HttpStatus.OK);


    }



}
