package com.devpro.sinopsefs.config.security;

import com.devpro.sinopsefs.model.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TokenServiceTest {

    @InjectMocks
    private TokenService tokenServiceMock;

    @BeforeEach
    private void setUp() {
        ReflectionTestUtils.setField(tokenServiceMock, "secret", "123");
    }

    @Test
    void quandoGerarTokenForChamadoDeveRetornarToken() {
        String token = tokenServiceMock.gerarToken(new Usuario());
        assertNotNull(token);
    }

    @Test
    void quandoGetSubjectForChamadoDeveRetornarLogin() {
        Usuario usuario = new Usuario();
        usuario.setLogin("user");
        String token = tokenServiceMock.gerarToken(usuario);
        String login = tokenServiceMock.getSubject(token);

        assertEquals(usuario.getLogin(), login);
    }

    @Test
    void quandoGetSubjectForChamadoPassandoTokenInvalidoDeveRetornarRuntimeException() {
        String msgEsperada = "Token jwt invalido ou expirado";

        RuntimeException exception = assertThrows(RuntimeException.class, () -> tokenServiceMock.getSubject(""));

        assertEquals(msgEsperada, exception.getMessage());
    }
}
