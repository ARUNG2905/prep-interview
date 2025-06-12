package com.project.prepinterview.dto.mapper;

import com.project.prepinterview.dto.request.UserRequest;
import com.project.prepinterview.dto.response.UserResponse;
import com.project.prepinterview.entity.User;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserMapper {
public User toEntity(UserRequest source,User target){
    if(target ==null){
        return target;
    }
    target.setUserName(source.userName());
    target.setEmail(source.email());
    return target;
}

public UserResponse toResponse(User user,String rawPassword){
    if(user == null){
        return null;
    }
    return new UserResponse(
            user.getUserId(),
            user.getUserName(),
            user.getEmail(),
            rawPassword


    );
}
}
