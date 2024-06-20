package com.api.projeto_final.controller.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdviceException {

    @ExceptionHandler(ExceptionPersonalizada.class)
    public ResponseEntity conflictException(ExceptionPersonalizada ex) {
        return ResponseEntity.status(ex.getStatus()).build();
    }
}