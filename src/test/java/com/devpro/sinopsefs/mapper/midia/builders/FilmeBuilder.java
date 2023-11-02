package com.devpro.sinopsefs.mapper.midia.builders;

import com.devpro.sinopsefs.model.Filme;

import java.util.ArrayList;

public class FilmeBuilder {

    private Filme filme;

    public static FilmeBuilder builder() {
        var builder = new FilmeBuilder();

        Filme filme = new Filme();
        filme.setId(810L);
        filme.setNome("matrix");
        filme.setSinopse("ação");
        filme.setGenero(new ArrayList<>());
        builder.filme = filme;

        return builder;
    }

    public Filme build() {
        return filme;
    }
}
