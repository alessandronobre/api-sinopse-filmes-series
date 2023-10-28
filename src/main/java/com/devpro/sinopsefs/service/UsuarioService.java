package com.devpro.sinopsefs.service;

import com.devpro.sinopsefs.dto.UsuarioDTO;
import com.devpro.sinopsefs.exceptions.ItemExistenteException;
import com.devpro.sinopsefs.exceptions.ItemNotFoundException;
import com.devpro.sinopsefs.mapper.UsuarioMapper;
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
    private UsuarioMapper usuarioMapper;

    private static final String USUARIO = "usuario";

    public UsuarioDTO editarUsuario(UsuarioDTO usuarioDto) {
        Usuario usuario = usuarioRepository.findById(usuarioDto.getId()).orElseThrow(() -> new ItemNotFoundException(usuarioDto.getLogin(), usuarioDto.getId()));
        String senhaEncoder = SenhaUtils.passwordEncoder().encode(usuarioDto.getSenha());
        usuario.setLogin(usuarioDto.getLogin());
        usuario.setSenha(senhaEncoder);
        usuarioRepository.save(usuario);
        return usuarioDto;
    }

    public void save(UsuarioDTO usuarioDto) {
        verificaLogin(usuarioDto.getLogin());
        String senhaEncoder = SenhaUtils.passwordEncoder().encode(usuarioDto.getSenha());
        usuarioDto.setSenha(senhaEncoder);
        Usuario usuario = usuarioMapper.converteDtoParaEntidade(usuarioDto);
        usuarioRepository.save(usuario);
    }

    private void verificaLogin(String login) {
        UserDetails usuario = usuarioRepository.findByLogin(login);

        if (usuario != null) {
            throw new ItemExistenteException(USUARIO, login);
        }
    }

    public void deletarUsuarioPorId(Long id) {
        usuarioRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(USUARIO, id));
        usuarioRepository.deleteById(id);
    }
}
