package com.devpro.sinopsefs.dto;

import com.devpro.sinopsefs.enums.Genero;
import lombok.Data;

import java.util.List;

@Data
public class MidiaDTO {

    private Long id;
    private String nome;
    private String sinopse;
    private List<Genero> genero;

}
