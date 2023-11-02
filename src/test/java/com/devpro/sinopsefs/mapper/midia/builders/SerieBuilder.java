package com.devpro.sinopsefs.mapper.midia.builders;

import com.devpro.sinopsefs.model.Serie;

import java.util.ArrayList;

public class SerieBuilder {

    private Serie serie;

    public static SerieBuilder builder() {
        var builder = new SerieBuilder();

        Serie serie = new Serie();
        serie.setId(810L);
        serie.setNome("matrix");
        serie.setSinopse("ação");
        serie.setGenero(new ArrayList<>());
        builder.serie = serie;

        return builder;
    }

    public Serie build() {
        return serie;
    }
}
