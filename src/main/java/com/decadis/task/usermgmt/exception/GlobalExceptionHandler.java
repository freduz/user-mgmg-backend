package com.decadis.task.usermgmt.exception;


import com.decadis.task.usermgmt.payload.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<com.decadis.task.usermgmt.payload.ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest){
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(exception.getMessage())
                .details(webRequest.getDescription(false))
                .timestamp(new Date())
                .build();

        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CommonException.class)
    public ResponseEntity<ErrorResponse> handleCommonException(CommonException exception,WebRequest webRequest){
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(exception.getMessage())
                .timestamp(new Date())
                .details(webRequest.getDescription(false))
                .build();

        return new ResponseEntity<>(errorResponse,exception.getHttpStatus());
    }
}
