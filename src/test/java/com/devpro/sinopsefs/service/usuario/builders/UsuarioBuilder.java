package com.devpro.sinopsefs.service.usuario.builders;

import com.devpro.sinopsefs.model.Usuario;

public class UsuarioBuilder {

    private Usuario usuario;

    public static UsuarioBuilder builder() {
        var builder = new UsuarioBuilder();

        var id = 810L;
        var login = "loginTest";
        var senha = "123";
        var usuario = new Usuario(id, login, senha);

        builder.usuario = usuario;

        return builder;
    }

    public Usuario build() {
        return usuario;
    }

}
