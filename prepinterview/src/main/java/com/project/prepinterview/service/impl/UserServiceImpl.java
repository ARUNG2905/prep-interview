package com.project.prepinterview.service.impl;

import com.project.prepinterview.dto.mapper.UserMapper;
import com.project.prepinterview.dto.request.UserRequest;
import com.project.prepinterview.dto.response.UserResponse;
import com.project.prepinterview.entity.Admin;
import com.project.prepinterview.entity.Candidate;
import com.project.prepinterview.entity.User;
import com.project.prepinterview.enums.UserRole;
import com.project.prepinterview.exceptions.UserNotFoundByEmailException;
import com.project.prepinterview.repository.UserRepository;
import com.project.prepinterview.service.contract.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserResponse registerCandidate(UserRequest request) {
        User user = userMapper.toEntity(request,new Candidate());
        String rawPassword = request.password();
        String encodedpassword = passwordEncoder.encode(rawPassword);
        user.setRole(UserRole.CANDIDATE);
        user.setPassword(encodedpassword);
        userRepository.save(user);
        return userMapper.toResponse(user,rawPassword);
    }

    @Override
    public UserResponse registerAdmin(UserRequest request) {
        User user = userMapper.toEntity(request,new Admin());
        String rawPassword = request.password();
        String encodedpassword = passwordEncoder.encode(rawPassword);
        user.setRole(UserRole.ADMIN);
        user.setPassword(encodedpassword);
        userRepository.save(user);

        return userMapper.toResponse(user,rawPassword);
    }

    @Override
    public void updateUsername(String newUsername) {
        User user = getcurrentUser();
       user.setUserName(newUsername);
        userRepository.save(user);

    }

    @Override
    public void updatePassword(String newPassword) {
        User user = getcurrentUser();
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

    private User getcurrentUser(){
        String email =  SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundByEmailException("User Not Found !!!"));
}

}
