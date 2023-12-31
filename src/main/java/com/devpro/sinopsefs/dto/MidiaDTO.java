package com.devpro.sinopsefs.dto;

import com.devpro.sinopsefs.enums.Genero;
import com.devpro.sinopsefs.model.Filme;
import com.devpro.sinopsefs.model.Serie;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MidiaDTO extends RepresentationModel<MidiaDTO> {

    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String sinopse;

    @NotNull
    private List<Genero> genero;

}
