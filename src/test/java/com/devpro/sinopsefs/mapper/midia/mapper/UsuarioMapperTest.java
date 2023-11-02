package com.devpro.sinopsefs.mapper.midia.mapper;

import com.devpro.sinopsefs.mapper.UsuarioMapper;
import com.devpro.sinopsefs.mapper.midia.builders.UsuarioDtoBuilder;
import com.devpro.sinopsefs.model.Usuario;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UsuarioMapperTest {

    @InjectMocks
    private UsuarioMapper UsuarioMapperMock;

    private static final Long ID = 810L;

    @Test
    void quandoConverteDtoParaEntidadeForChamadoPassandoUsuarioDTODeveRetornarUsuario() {
        Usuario result = UsuarioMapperMock.converteDtoParaEntidade(UsuarioDtoBuilder.builder().build());

        assertEquals(result.getId(), ID);
    }
}
