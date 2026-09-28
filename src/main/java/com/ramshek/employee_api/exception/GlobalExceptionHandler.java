package com.ramshek.employee_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleNotFount(EmployeeNotFoundException e){

        Map<String,String> error = new HashMap<>();

        error.put("message",e.getMessage());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValidation(MethodArgumentNotValidException e){

        Map<String,String> errors = new HashMap<>();

        e.getBindingResult()
                .getFieldErrors()
                .forEach(
                        error -> errors.put(error.getField(),error.getDefaultMessage())
                );
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }


}
