package com.project.prepinterview.dto.mapper;

import com.project.prepinterview.dto.request.TestRequest;
import com.project.prepinterview.dto.response.TestResponse;
import com.project.prepinterview.entity.Test;
import com.project.prepinterview.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
public class TestMapper {
    public Test toEntity(TestRequest source,Test target){
        if(target ==null){
            return target;
        }
        target.setUser(source.userName());
        target.setTestType(source.testType());
        return target;
    }

    public TestResponse toResponse(Test test, User userName){
        if(test==null || userName==null){
            return null;
        }
        return new TestResponse(
                test.getTestId(),
                test.getUser(),
                test.getUserMarks(),
                test.getTestType()
        );

    }
}
