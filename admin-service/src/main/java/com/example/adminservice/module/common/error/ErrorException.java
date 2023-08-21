package com.example.adminservice.module.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorException extends RuntimeException{
    private final AbstractErrorCode abstractErrorCode;
    private final String method;
}
