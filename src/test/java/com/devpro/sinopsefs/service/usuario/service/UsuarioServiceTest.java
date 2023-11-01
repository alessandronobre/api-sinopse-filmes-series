package com.devpro.sinopsefs.service.usuario.service;

import com.devpro.sinopsefs.dto.UsuarioDTO;
import com.devpro.sinopsefs.exceptions.ItemExistenteException;
import com.devpro.sinopsefs.exceptions.ItemNotFoundException;
import com.devpro.sinopsefs.mapper.UsuarioMapper;
import com.devpro.sinopsefs.model.Usuario;
import com.devpro.sinopsefs.repository.UsuarioRepository;
import com.devpro.sinopsefs.service.UsuarioService;
import com.devpro.sinopsefs.service.usuario.builders.UsuarioBuilder;
import com.devpro.sinopsefs.service.usuario.builders.UsuarioDtoBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioServiceMock;

    @Mock
    private UsuarioRepository usuarioRepositoryMock;

    @Mock
    private UsuarioMapper usuarioMapperMock;

    private static final Long ID = 810L;
    private static final String LOGIN = "loginTest";

    @Test
    void quandoEditarUsuarioForChamadoPassandoIdValidoDeveRetornarUsuarioDto() {
        Usuario usuario = UsuarioBuilder.builder().build();
        when(usuarioRepositoryMock.findById(ID)).thenReturn(Optional.ofNullable(usuario));
        when(usuarioRepositoryMock.save(any())).thenReturn(null);

        UsuarioDTO result = usuarioServiceMock.editarUsuario(UsuarioDtoBuilder.builder().build());

        assertEquals(result.getId(), usuario.getId());
        verify(usuarioRepositoryMock, times(1)).findById(ID);
        verify(usuarioRepositoryMock, times(1)).save(any());

    }

    @Test
    void quandoEditarUsuarioForChamadoPassandoIdInvalidoDeveRetornarItemNotFoundException() {
        when(usuarioRepositoryMock.findById(ID)).thenReturn(Optional.ofNullable(null));
        String msgEsperada = "loginTest de id 810 não encontrado";

        ItemNotFoundException exception = assertThrows(ItemNotFoundException.class, () -> usuarioServiceMock
                .editarUsuario(UsuarioDtoBuilder.builder().build()));

        assertEquals(msgEsperada, exception.getMessage());
        verify(usuarioRepositoryMock, times(1)).findById(ID);
    }

    @Test
    void quandoSaveForChamadoPassandoUsuarioValidoDeveSalvarUsuario() {
        when(usuarioRepositoryMock.findByLogin(LOGIN)).thenReturn(null);
        when(usuarioMapperMock.converteDtoParaEntidade(any())).thenReturn(UsuarioBuilder.builder().build());
        when(usuarioRepositoryMock.save(any())).thenReturn(null);

        usuarioServiceMock.save(UsuarioDtoBuilder.builder().build());

        verify(usuarioRepositoryMock, times(1)).findByLogin(LOGIN);
        verify(usuarioMapperMock, times(1)).converteDtoParaEntidade(any());
        verify(usuarioRepositoryMock, times(1)).save(any());

    }

    @Test
    void quandoSaveForChamadoPassandoUsuarioInvalidoDeveRetornarItemExistenteException() {
        when(usuarioRepositoryMock.findByLogin(LOGIN)).thenReturn(UsuarioBuilder.builder().build());
        String msgEsperada = "Já existe usuario com o nome loginTest cadastrado";

        ItemExistenteException exception = assertThrows(ItemExistenteException.class, () -> usuarioServiceMock
                .save(UsuarioDtoBuilder.builder().build()));

        assertEquals(msgEsperada, exception.getMessage());
        verify(usuarioRepositoryMock, times(1)).findByLogin(LOGIN);
    }

    @Test
    void quandoDeletarUsuarioPorIdForChamadoPassandoIdValidoDeveDeletarUsuario() {
        Usuario usuario = UsuarioBuilder.builder().build();
        when(usuarioRepositoryMock.findById(ID)).thenReturn(Optional.ofNullable(usuario));
        doNothing().when(usuarioRepositoryMock).deleteById(ID);

        usuarioServiceMock.deletarUsuarioPorId(ID);

        verify(usuarioRepositoryMock, times(1)).findById(ID);
        verify(usuarioRepositoryMock, times(1)).deleteById(ID);
    }

    @Test
    void quandoDeletarUsuarioPorIdForChamadoPassandoIdInvalidoDeveRetornarItemNotFoundException() {
        when(usuarioRepositoryMock.findById(ID)).thenReturn(Optional.ofNullable(null));
        String msgEsperada = "usuario de id 810 não encontrado";

        ItemNotFoundException exception = assertThrows(ItemNotFoundException.class, () -> usuarioServiceMock
                .deletarUsuarioPorId(ID));

        assertEquals(msgEsperada, exception.getMessage());
        verify(usuarioRepositoryMock, times(1)).findById(ID);
    }
}
