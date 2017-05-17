package com.pearson.codingchallenge.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.pearson.codingchallenge.exception.ErrorMessage;

@ControllerAdvice
public class ServiceExceptionHandler extends ResponseEntityExceptionHandler {
 
    @ExceptionHandler(value = { ServiceException.class })
    @ResponseBody
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, new ErrorMessage(ex.getMessage()), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}