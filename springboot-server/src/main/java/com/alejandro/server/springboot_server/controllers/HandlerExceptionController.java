package com.alejandro.server.springboot_server.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.alejandro.server.springboot_server.DTO.Error;
import com.alejandro.server.springboot_server.exceptions.ProductNotFoundException;

@RestControllerAdvice
public class HandlerExceptionController {

    @ExceptionHandler({NoHandlerFoundException.class})
    public ResponseEntity<Error> notFoundEx(Exception ex){
        Error error = new Error();
        error.setDate(new Date());
        error.setError("API no encontrada");
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(404).body(error);
    }

    @ExceptionHandler({ProductNotFoundException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> productNotFoundException(Exception ex){
        Map<String, Object> error = new HashMap<>();
        error.put("date",new Date());
        error.put("error", "El id del producto que esta buscando no se encuentra disponible");
        error.put("message", ex.getMessage());
        error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return error;
    }

}
