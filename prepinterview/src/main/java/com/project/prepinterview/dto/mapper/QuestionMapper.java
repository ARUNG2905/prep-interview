package com.project.prepinterview.dto.mapper;

import com.project.prepinterview.dto.request.QuestionRequest;
import com.project.prepinterview.dto.response.QuestionResponse;
import com.project.prepinterview.entity.Question;
import org.springframework.stereotype.Controller;

@Controller
public class QuestionMapper {

    public Question toEntity(QuestionRequest source,Question target){
        if (target == null){
            return target;
        }

        target.setQuestion(source.question());
        target.setOptions(source.options());
        target.setCorrectAnswer(source.correctAnswer());
        target.setDifficulty(source.difficulty());
        target.setCategory(source.category());

        return target;
    }


    public QuestionResponse toResponse(Question question){
        if (question == null){
            return null;
        }
        return new QuestionResponse(
                question.getQuestionId(),
                question.getQuestion(),
                question.getOptions(),
                question.getCorrectAnswer(),
                question.getDifficulty(),
                question.getCategory(),
                question.getCreatedAt().toEpochMilli()
        );
    }
}
