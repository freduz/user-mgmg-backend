package com.decadis.task.usermgmt.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CommonException extends RuntimeException{
    private HttpStatus httpStatus;
    public CommonException(HttpStatus httpStatus,String message){
        super(message);
        this.httpStatus = httpStatus;
    }
}
