package com.micro.patient_service.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log =  LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValidationException(MethodArgumentNotValidException ex){
        Map<String,String> errors= new HashMap<>();;
        ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(EmailAlreadyExistException.class)
    public ResponseEntity<Map<String,String>> handleEmailAlreadyExistsException(EmailAlreadyExistException ex){
        Map<String,String> map = new HashMap<>();
        log.warn("Email address already exist {}", ex.getMessage());
        map.put("message", "Email already exists");
        return ResponseEntity.badRequest().body(map);
    }
    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<Map<String,String>> handlePatientNotExistsException(PatientNotFoundException ex){
        Map<String,String> map = new HashMap<>();
        log.warn("Patient Not Found {}", ex.getMessage());
        map.put("message", "Patient Not Found");
        return ResponseEntity.badRequest().body(map);
    }
}
