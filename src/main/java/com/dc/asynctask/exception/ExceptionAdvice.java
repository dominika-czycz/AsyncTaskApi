package com.dc.asynctask.exception;


import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice
@Log4j2
public class ExceptionAdvice {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Exception> handleNotFoundException(NotFoundException ex) {
        return getResponse(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<Object> onMethodArgumentNotValidException(
            MethodArgumentNotValidException ex) {
        return new ResponseEntity<>(new ValidExceptionResponse(ex.getDetailMessageArguments()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Exception> handleException(RuntimeException ex) {
        return getResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<Exception> getResponse(Exception ex, HttpStatus httpStatus) {
        log.error("Returning exception to the client", ex);
        return new ResponseEntity<>(ex, httpStatus);
    }

}
