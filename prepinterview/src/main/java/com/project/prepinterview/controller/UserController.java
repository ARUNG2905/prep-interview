package com.project.prepinterview.controller;

import com.project.prepinterview.dto.request.LoginRequest;
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
import java.util.Random;

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

@GetMapping("user/generateotp")
public ResponseEntity<String> generateotp(@RequestParam String username){
    userService.generateotp(username);
return new ResponseEntity<>("otp is generated",HttpStatus.OK);

}
    @PutMapping("user/forgotpassword")
    public ResponseEntity<String> forgotPassword(@RequestParam String userName,@RequestParam String newPassword,@RequestParam String otp){

        userService.forgotPassword(userName,newPassword,otp);
        return new ResponseEntity<>("new password is update",HttpStatus.OK);
    }
@PostMapping("/user/candidate-login")
   public ResponseEntity<ResponseStructure<UserResponse>> loginCandidate(@RequestBody LoginRequest loginRequest){
        UserResponse userResponse = userService.loginCandidate(loginRequest.email(), loginRequest.password());
        ResponseStructure<UserResponse> responseStructure = new ResponseStructure<>(HttpStatus.OK.value(), "Login Succesfull",userResponse);
        return new ResponseEntity<ResponseStructure<UserResponse>>(responseStructure,HttpStatus.OK);
}

    @PostMapping("/user/admin-login")
    public ResponseEntity<ResponseStructure<UserResponse>> loginAdmin(@RequestBody LoginRequest loginRequest){
        UserResponse userResponse = userService.loginAdmin(loginRequest.email(), loginRequest.password());
        ResponseStructure<UserResponse> responseStructure = new ResponseStructure<>(HttpStatus.OK.value(), "Login Succesfull",userResponse);
        return new ResponseEntity<ResponseStructure<UserResponse>>(responseStructure,HttpStatus.OK);
    }


}
