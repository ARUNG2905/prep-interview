package com.project.prepinterview.service.contract;

import com.project.prepinterview.dto.request.UserRequest;
import com.project.prepinterview.dto.response.UserResponse;

import java.util.List;

public interface UserService {


    UserResponse registerCandidate(UserRequest request);

    UserResponse registerAdmin(UserRequest request);


    void updateUsername(String newUsername);

    void updatePassword(String newPassword);

    void generateotp(String username);

    void forgotPassword(String userName,String newPassword, String otp);

    UserResponse loginCandidate(String userName, String password);

    UserResponse loginAdmin(String email, String password);
}
