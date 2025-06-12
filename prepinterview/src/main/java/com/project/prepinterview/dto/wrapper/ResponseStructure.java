package com.project.prepinterview.dto.wrapper;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class ResponseStructure<T> {
    private int status;
    private String Message;
    private T data;
}
