package com.dc.asynctask.exception;

import com.dc.asynctask.annotation.HttpStatus;

@HttpStatus(org.springframework.http.HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
