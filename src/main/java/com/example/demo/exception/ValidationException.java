package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

<<<<<<< HEAD
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ValidationException extends Exception {
    public ValidationException(String message) {
        super(message);
    }
=======
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ValidationException extends Exception{
    public ValidationException(String message){
        super(message);
    }

>>>>>>> 7d01be94d9229d365aa719d10ade119f987f3322
}