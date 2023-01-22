package com.dc.asynctask.api;

import com.dc.asynctask.annotation.HttpStatus;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Log4j2
public class ExceptionAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleException(RuntimeException ex, WebRequest webRequest) {
        log.error("Returning exception to the client", ex);
        return handleExceptionInternal(ex, ex, HttpHeaders.EMPTY, calculateHttpStatus(ex), webRequest);
    }

    private org.springframework.http.HttpStatus calculateHttpStatus(RuntimeException ex) {
        return (ex.getClass().isAnnotationPresent(HttpStatus.class)) ? ex.getClass().getAnnotation(HttpStatus.class).value()
                : org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
