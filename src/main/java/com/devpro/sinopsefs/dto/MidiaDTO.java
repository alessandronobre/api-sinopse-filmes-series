package com.devpro.sinopsefs.dto;

import com.devpro.sinopsefs.enums.Genero;
import com.devpro.sinopsefs.model.Filme;
import com.devpro.sinopsefs.model.Serie;
import lombok.Data;

import java.util.List;

@Data
public class MidiaDTO {

    private Long id;
    private String nome;
    private String sinopse;
    private List<Genero> genero;

    public MidiaDTO(Filme filme) {
        this.id = filme.getId();
        this.nome = filme.getNome();
        this.sinopse = filme.getSinopse();
        this.genero = filme.getGenero();
    }

    public MidiaDTO(Serie serie) {
        this.id = serie.getId();
        this.nome = serie.getNome();
        this.sinopse = serie.getSinopse();
        this.genero = serie.getGenero();
    }
}
