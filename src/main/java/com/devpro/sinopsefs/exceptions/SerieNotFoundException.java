package com.devpro.sinopsefs.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class SerieNotFoundException extends EntityNotFoundException {

    public SerieNotFoundException(){
        super("Nenhuma serie cadastrada na base");
    }

    public SerieNotFoundException(String nome){
        super(String.format("Serie %s não encontrada", nome));
    }

    public SerieNotFoundException(Long id){
        super(String.format("Serie de id %d não encontrada", id));
    }
}
