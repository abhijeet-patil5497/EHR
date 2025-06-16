package com.ehr.patientmanagement.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log= LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleException(MethodArgumentNotValidException ex){

        Map<String, String> errors= new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(fieldError -> errors.put(fieldError.getField(), fieldError.getDefaultMessage()));

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<Map<String,String>> emailAlreadyExistsException(EmailAlreadyExistsException ex){

        log.warn("Email already exists");
        Map<String,String> errors= new HashMap<>();
        errors.put("Message", "Email already exists with this mail id");

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(PatientNotFoundException.class)
    public  ResponseEntity<Map<String,String>> patientNotFoundException(PatientNotFoundException ex){

        log.warn("Patient does not exist with given id");
        Map<String,String> errors = new HashMap<>();
        errors.put("Message", "Patient not found for given id");

        return  ResponseEntity.badRequest().body(errors);

    }


}
