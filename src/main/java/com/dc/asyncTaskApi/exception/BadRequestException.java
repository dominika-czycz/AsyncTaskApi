package com.dc.asyncTaskApi.exception;

import com.dc.asyncTaskApi.annotation.HttpStatus;

@HttpStatus(org.springframework.http.HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
