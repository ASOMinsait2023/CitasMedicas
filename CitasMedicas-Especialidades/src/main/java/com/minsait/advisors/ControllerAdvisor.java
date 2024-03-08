package com.minsait.advisors;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ControllerAdvisor {
    @ExceptionHandler( NoSuchElementException.class)
    public ResponseEntity<?> notFoundException(NoSuchElementException ex){
        var responce = new HashMap<String, Object>();
        responce.put("error", ex.getMessage());
        return new ResponseEntity<>(responce, HttpStatus.NOT_FOUND);
    }

}
