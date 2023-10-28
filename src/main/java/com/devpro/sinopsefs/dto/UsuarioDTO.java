package com.devpro.sinopsefs.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UsuarioDTO {
    private Long id;

    @NotBlank
    private String login;

    @NotBlank
    private String senha;

}
