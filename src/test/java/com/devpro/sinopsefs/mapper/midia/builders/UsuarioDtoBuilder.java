package com.devpro.sinopsefs.mapper.midia.builders;

import com.devpro.sinopsefs.dto.UsuarioDTO;

public class UsuarioDtoBuilder {

    private UsuarioDTO usuario;

    public static UsuarioDtoBuilder builder() {
        var builder = new UsuarioDtoBuilder();

        var id = 810L;
        var login = "loginTest";
        var senha = "123";
        var usuario = new UsuarioDTO(id, login, senha);

        builder.usuario = usuario;

        return builder;
    }

    public UsuarioDTO build() {
        return usuario;
    }
}
