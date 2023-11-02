package com.devpro.sinopsefs.mapper.midia.builders;

import com.devpro.sinopsefs.dto.MidiaDTO;
import com.devpro.sinopsefs.enums.Genero;

import java.util.ArrayList;
import java.util.List;

public class MidiaDtoBuilder {

    private MidiaDTO midia;

    public static MidiaDtoBuilder builder() {
        var builder = new MidiaDtoBuilder();

        Long id = 810L;
        String nome = "matrix";
        String sinopse = "ação";
        List<Genero> generos = new ArrayList<>();
        MidiaDTO midia = new MidiaDTO(id, nome, sinopse, generos);

        builder.midia = midia;

        return builder;
    }

    public MidiaDTO build() {
        return midia;
    }
}
