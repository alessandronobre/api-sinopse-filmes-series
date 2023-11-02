package com.devpro.sinopsefs.mapper;

import com.devpro.sinopsefs.dto.MidiaDTO;
import com.devpro.sinopsefs.model.Filme;
import com.devpro.sinopsefs.model.Serie;
import org.springframework.stereotype.Component;

@Component
public class MidiaMapper {

    public MidiaDTO converteFilmeEntidadeParaMidiaDto(Filme filme) {
        MidiaDTO midia = new MidiaDTO();
        midia.setId(filme.getId());
        midia.setNome(filme.getNome());
        midia.setSinopse(filme.getSinopse());
        midia.setGenero(filme.getGenero());
        return midia;
    }

    public MidiaDTO converteSerieEntidadeParaMidiaDto(Serie serie) {
        MidiaDTO midia = new MidiaDTO();
        midia.setId(serie.getId());
        midia.setNome(serie.getNome());
        midia.setSinopse(serie.getSinopse());
        midia.setGenero(serie.getGenero());
        return midia;
    }

    public Filme converteMidiaDtoParaFilmeEntidade(MidiaDTO midia) {
        Filme filme = new Filme();
        filme.setId(midia.getId());
        filme.setNome(midia.getNome());
        filme.setSinopse(midia.getSinopse());
        filme.setGenero(midia.getGenero());
        return filme;
    }

    public Serie converteMidiaDtoParaSerieEntidade(MidiaDTO midia) {
        Serie serie = new Serie();
        serie.setId(midia.getId());
        serie.setNome(midia.getNome());
        serie.setSinopse(midia.getSinopse());
        serie.setGenero(midia.getGenero());
        return serie;
    }
}
