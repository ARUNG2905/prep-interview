package com.project.prepinterview.service.impl;

import com.project.prepinterview.dto.mapper.UserMapper;
import com.project.prepinterview.dto.request.UserRequest;
import com.project.prepinterview.dto.response.UserResponse;
import com.project.prepinterview.entity.Admin;
import com.project.prepinterview.entity.Candidate;
import com.project.prepinterview.entity.User;
import com.project.prepinterview.enums.UserRole;
import com.project.prepinterview.exceptions.BadRoleCredentialException;
import com.project.prepinterview.exceptions.UserNotFoundByEmailException;
import com.project.prepinterview.repository.UserRepository;
import com.project.prepinterview.service.contract.UserService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private final Map<String, String> otpStore = new ConcurrentHashMap<>();
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

    @Override
    public void generateotp(String username) {
        User user = userRepository.findByEmail(username).orElseThrow(()-> new UserNotFoundByEmailException("Email Not found please register"));
        String otp = String.valueOf(999*(new Random().nextInt(1,10)));
//        System.out.println(otp);
        otpStore.put(otp,username);
        sendOtpEmail(username,otp);


    }

    @Override
    public void forgotPassword(String userName,String newPassword, String otp) {
       User user = userRepository.findByEmail(userName).orElseThrow(()-> new UserNotFoundByEmailException("User not found"));
                    if(otpStore.get(otp).equals(otp)) {
                        String encodedPassword = passwordEncoder.encode(newPassword);
                        user.setPassword(encodedPassword);
                        otpStore.remove(otp);
                        userRepository.save(user);

                    }

    }

    @Override
    public UserResponse loginCandidate(String userName, String password) {
        User user = userRepository.findByEmail(userName).orElseThrow(()-> new UserNotFoundByEmailException("User not found"));

        if(!user.getRole().equals(UserRole.CANDIDATE)){
            throw new BadRoleCredentialException("Please Login as Candidate");
        }
        if(!passwordEncoder.matches(password,user.getPassword())){
            throw new BadRoleCredentialException("please Enter Correct Password");
        }
        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);
        return userMapper.toResponse(user, password);
    }

    @Override
    public UserResponse loginAdmin(String email, String password) {
        User user = userRepository.findByEmail(email).orElseThrow(()-> new UserNotFoundByEmailException("User not found"));

        if(!user.getRole().equals(UserRole.ADMIN)){
            throw new BadRoleCredentialException("Please Login as Candidate");
        }
        if(!passwordEncoder.matches(password,user.getPassword())){
            throw new BadRoleCredentialException("please Enter Correct Password");
        }
        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);
        return userMapper.toResponse(user,password);

    }

    private User getcurrentUser(){
        String email =  SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundByEmailException("User Not Found !!!"));
}

    @Autowired
    private JavaMailSender mailSender;


    public void sendOtpEmail(String toEmail, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("OTP for Password Reset");
        message.setText("Your OTP is: " + otp);
        mailSender.send(message);
    }

}
