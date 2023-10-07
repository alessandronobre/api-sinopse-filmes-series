package com.devpro.sinopsefs.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class MidiaExistingException extends RuntimeException {

    public MidiaExistingException(String tipoMidia, String nome){
        super(String.format("Já existe %s com o nome %s cadastrado", tipoMidia, nome));
    }
}
