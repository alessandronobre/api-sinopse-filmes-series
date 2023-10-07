package com.devpro.sinopsefs.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class MidiaNotFoundException extends EntityNotFoundException {

    public MidiaNotFoundException(String tipoMidia){
        super(String.format("Nenhum(a) %s cadastrado na base", tipoMidia));
    }

    public MidiaNotFoundException(String tipoMidia, String nome){
        super(String.format("%s %s não encontrado", tipoMidia, nome));
    }

    public MidiaNotFoundException(String tipoMidia, Long id){
        super(String.format("%s de id %d não encontrado", tipoMidia, id));
    }
}
