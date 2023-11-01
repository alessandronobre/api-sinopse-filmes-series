package com.devpro.sinopsefs.service.usuario.service;

import com.devpro.sinopsefs.model.Usuario;
import com.devpro.sinopsefs.repository.UsuarioRepository;
import com.devpro.sinopsefs.service.AutenticacaoService;
import com.devpro.sinopsefs.service.usuario.builders.UsuarioBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AutenticacaoServiceTest {

    @InjectMocks
    private AutenticacaoService autenticacaoServiceMock;

    @Mock
    private UsuarioRepository usuarioRepositoryMock;

    private static final String LOGIN = "loginTest";

    @Test
    void quandoLoadUserByUsernameForChamadoPassandoLoginValidoDeveRetornarUsuario() {
        Usuario usuario = UsuarioBuilder.builder().build();
        when(usuarioRepositoryMock.findByLogin(LOGIN)).thenReturn(usuario);

        UserDetails result = autenticacaoServiceMock.loadUserByUsername(LOGIN);

        assertEquals(result.getUsername(), usuario.getUsername());
        verify(usuarioRepositoryMock, times(1)).findByLogin(LOGIN);

    }

}
