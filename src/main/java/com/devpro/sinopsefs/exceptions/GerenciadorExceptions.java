package com.devpro.sinopsefs.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<CampoErro> erros = new ArrayList<>();
        exception.getBindingResult().getFieldErrors().forEach(fieldErro -> {
            CampoErro campoErro = new CampoErro(
                    fieldErro.getField(),
                    fieldErro.getDefaultMessage()
            );

            erros.add(campoErro);
        });

        ValidacaoApiErro validacaoApiErro = new ValidacaoApiErro(
                status.value(),
                status.toString(),
                LocalDateTime.now(),
                "Houveram erros de validação",
                erros
        );
        return new ResponseEntity<>(validacaoApiErro, status);
    }
}
