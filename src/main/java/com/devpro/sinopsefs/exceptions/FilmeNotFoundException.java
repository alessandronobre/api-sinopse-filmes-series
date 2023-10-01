package com.devpro.sinopsefs.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class FilmeNotFoundException extends EntityNotFoundException {

    public FilmeNotFoundException(){
        super("Nenhum filme cadastrado na base");
    }

    public FilmeNotFoundException(String nome){
        super(String.format("Filme %s não encontrado", nome));
    }

    public FilmeNotFoundException(Long id){
        super(String.format("Filme de id %d não encontrado", id));
    }
}
