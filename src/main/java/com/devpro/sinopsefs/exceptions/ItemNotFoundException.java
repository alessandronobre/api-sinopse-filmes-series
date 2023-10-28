package com.devpro.sinopsefs.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ItemNotFoundException extends EntityNotFoundException {

    public ItemNotFoundException(String item){
        super(String.format("Nenhum(a) %s cadastrado na base", item));
    }

    public ItemNotFoundException(String item, String nome){
        super(String.format("%s %s não encontrado", item, nome));
    }

    public ItemNotFoundException(String item, Long id){
        super(String.format("%s de id %d não encontrado", item, id));
    }
}
