package com.devpro.sinopsefs.service;

import com.devpro.sinopsefs.dto.UsuarioDTO;
import com.devpro.sinopsefs.mapper.ToMapper;
import com.devpro.sinopsefs.model.Usuario;
import com.devpro.sinopsefs.repository.UsuarioRepository;
import com.devpro.sinopsefs.utils.SenhaUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioService {

    private UsuarioRepository usuarioRepository;
    private ToMapper toMapper;

    public void save(UsuarioDTO usuarioDto) {
        verificaLogin(usuarioDto.getLogin());
        String senhaEncoder = SenhaUtils.passwordEncoder().encode(usuarioDto.getSenha());
        usuarioDto.setSenha(senhaEncoder);
        Usuario usuario = toMapper.converteUsuario(usuarioDto);
        usuarioRepository.save(usuario);
    }

    private void verificaLogin(String login) {
        UserDetails usuario = usuarioRepository.findByLogin(login);

        if (usuario != null) {
            throw new RuntimeException("Usuario ja cadastrado");
        }
    }
}
