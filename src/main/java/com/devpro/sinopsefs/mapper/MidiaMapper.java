package com.devpro.sinopsefs.mapper;

import com.devpro.sinopsefs.dto.MidiaDTO;
import com.devpro.sinopsefs.model.Filme;
import com.devpro.sinopsefs.model.Serie;
import org.springframework.stereotype.Component;

@Component
public class MidiaMapper {

    public MidiaDTO converteFilmeEntidadeParaDto(Filme filme) {
        MidiaDTO midia = new MidiaDTO();
        midia.setId(filme.getId());
        midia.setNome(filme.getNome());
        midia.setSinopse(filme.getSinopse());
        midia.setGenero(filme.getGenero());
        return midia;
    }

    public MidiaDTO converteSerieEntidadeParaDto(Serie serie) {
        MidiaDTO midia = new MidiaDTO();
        midia.setId(serie.getId());
        midia.setNome(serie.getNome());
        midia.setSinopse(serie.getSinopse());
        midia.setGenero(serie.getGenero());
        return midia;
    }
}
