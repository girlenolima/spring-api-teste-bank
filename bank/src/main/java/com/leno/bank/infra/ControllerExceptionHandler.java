package com.leno.bank.infra;


import com.leno.bank.dtos.ExceptionDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity duplicateEntry(DataIntegrityViolationException e){

        ExceptionDTO exceptionDTO = new ExceptionDTO("Usuario ja cadastrado","400");
        return ResponseEntity.badRequest().body(exceptionDTO);

    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity notFoundEntity(EntityNotFoundException e){

        ExceptionDTO exceptionDTO = new ExceptionDTO("Usuario ja cadastrado","400");
        return ResponseEntity.notFound().build();

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity genericException(Exception e){

        ExceptionDTO exceptionDTO = new ExceptionDTO(e.getMessage(),"500");
        return ResponseEntity.internalServerError().body(exceptionDTO);

    }






}
