package com.devpro.sinopsefs.controller;

import com.devpro.sinopsefs.config.security.TokenService;
import com.devpro.sinopsefs.dto.UsuarioDTO;
import com.devpro.sinopsefs.hateoas.UsuarioAssembler;
import com.devpro.sinopsefs.model.Usuario;
import com.devpro.sinopsefs.service.UsuarioService;
import com.devpro.sinopsefs.service.usuario.builders.UsuarioBuilder;
import com.devpro.sinopsefs.service.usuario.builders.UsuarioDtoBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioControllerTest {

    @InjectMocks
    private UsuarioController usuarioControllerMock;

    @Mock
    private TokenService tokenServiceMock;

    @Mock
    private UsuarioService usuarioServiceMock;

    @Mock
    private UsuarioAssembler usuarioAssemblerMock;

    @Mock
    private AuthenticationManager authenticationManagerMock;

    @Test
    void quandoLoginForChamadoDeveRetornoStatus200() {
        Usuario usuario = UsuarioBuilder.builder().build();
        Authentication authentication = new UsernamePasswordAuthenticationToken(usuario, null);

        when(authenticationManagerMock.authenticate(any())).thenReturn(authentication);
        when(tokenServiceMock.gerarToken(any())).thenReturn("");

        ResponseEntity resposta = usuarioControllerMock.login(usuario);

        assertEquals(200, resposta.getStatusCodeValue());
        verify(authenticationManagerMock, times(1)).authenticate(any());
        verify(tokenServiceMock, times(1)).gerarToken(any());
    }

    @Test
    void quandoCadastrarForChamadoDeveRetornarStatus201() {
        doNothing().when(usuarioServiceMock).save(any());

        ResponseEntity resposta = usuarioControllerMock.cadastrar(any());

        assertEquals(201, resposta.getStatusCodeValue());
        verify(usuarioServiceMock, times(1)).save(any());
    }

    @Test
    void quandoEditarUsuarioForChamadoPassandoDadosValidosDeveRetornarItemCadastrado() {
        UsuarioDTO usuario = UsuarioDtoBuilder.builder().build();
        EntityModel<UsuarioDTO> resource = EntityModel.of(usuario);
        when(usuarioAssemblerMock.toModel(any())).thenReturn(resource);

        EntityModel<UsuarioDTO> result = usuarioControllerMock.editarUsuario(usuario);

        assertEquals(resource.getContent().getId(), result.getContent().getId());
        verify(usuarioAssemblerMock, times(1)).toModel(any());
    }

    @Test
    void quandoDeletarUsuarioPorIdForChamadoDeveRetornarNoContent() {
        doNothing().when(usuarioServiceMock).deletarUsuarioPorId(any());

        ResponseEntity result = usuarioControllerMock.deletarUsuarioPorId(1L);

        assertEquals(ResponseEntity.noContent().build().getStatusCode(), result.getStatusCode());
        verify(usuarioServiceMock, times(1)).deletarUsuarioPorId(any());
    }
}
