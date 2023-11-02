package com.devpro.sinopsefs.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class SenhaUtilsTest {

    @InjectMocks
    private SenhaUtils senhaUtilsMock;

    @Test
    void quandoPasswordEncoderForChamadoDeveRetornarInstanciaBCryptPasswordEncoder() {
        PasswordEncoder encoder = SenhaUtils.passwordEncoder();

        assertNotNull(encoder);
        assertTrue(encoder instanceof BCryptPasswordEncoder);
    }
}
