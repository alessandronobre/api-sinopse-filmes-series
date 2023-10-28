package com.devpro.sinopsefs.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ItemExistenteException extends RuntimeException {

    public ItemExistenteException(String item, String nome){
        super(String.format("Já existe %s com o nome %s cadastrado", item, nome));
    }
}
