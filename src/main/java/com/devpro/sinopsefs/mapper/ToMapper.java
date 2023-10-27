package com.devpro.sinopsefs.mapper;

import com.devpro.sinopsefs.dto.UsuarioDTO;
import com.devpro.sinopsefs.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class ToMapper {

    public Usuario converteUsuario(UsuarioDTO usuarioDto) {
        Usuario usuario = new Usuario();
        usuario.setLogin(usuarioDto.getLogin());
        usuario.setSenha(usuarioDto.getSenha());
        return usuario;
    }
}
