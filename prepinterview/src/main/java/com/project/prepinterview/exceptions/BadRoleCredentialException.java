package com.project.prepinterview.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BadRoleCredentialException extends RuntimeException{
    String message;
}
