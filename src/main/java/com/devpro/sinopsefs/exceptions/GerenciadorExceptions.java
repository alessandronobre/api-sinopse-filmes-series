package com.devpro.sinopsefs.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice(annotations = RestController.class)
public class GerenciadorExceptions extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiErro> handlerEntityNotFoundException(EntityNotFoundException exception){
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        ApiErro apiErro = new ApiErro(
                httpStatus.value(),
                httpStatus.getReasonPhrase(),
                exception.getLocalizedMessage(),
                LocalDateTime.now());

        return new ResponseEntity<>(apiErro, httpStatus);
    }
}
