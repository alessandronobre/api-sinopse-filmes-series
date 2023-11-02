package com.devpro.sinopsefs.config.security;

import com.devpro.sinopsefs.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SecurityFilterTest {

    @InjectMocks
    private SecurityFilter securityFilterMock;

    @Mock
    private UsuarioRepository usuarioRepositoryMock;

    @Mock
    private TokenService tokenService;

    private static final String TOKEN = "token";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    @Test
    void quandoDoFilterInternalForChamadoPassandoAuthorizationValidoDeveRetornarSucesso() throws ServletException, IOException {
        FilterChain filterChain = mock(FilterChain.class);
        securityFilterMock = new SecurityFilter(tokenService, usuarioRepositoryMock);
        when(tokenService.getSubject(TOKEN)).thenReturn(USER);
        when(usuarioRepositoryMock.findByLogin(USER)).thenReturn(instanciaUserDetails());

        securityFilterMock.doFilterInternal(geraRequest(), new MockHttpServletResponse(), filterChain);

        assertTrue(SecurityContextHolder.getContext().getAuthentication() instanceof UsernamePasswordAuthenticationToken);
    }

    @Test
    void quandoDoFilterInternalForChamadoPassandoAuthorizationInvalidoDeveRetornarSucesso() throws ServletException, IOException {
        securityFilterMock = new SecurityFilter(null, null);
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        FilterChain filterChain = mock(FilterChain.class);

        securityFilterMock.doFilterInternal(request, response, filterChain);

        assertNull(request.getAttribute("SPRING_SECURITY_CONTEXT"));
        verify(filterChain, times(1)).doFilter(request, response);
    }

    private static MockHttpServletRequest geraRequest() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Authorization", "Bearer " + TOKEN);
        return request;
    }

    private static UserDetails instanciaUserDetails() {
        return  new User(USER, PASSWORD, List.of(new SimpleGrantedAuthority("ROLE_USER")));
    }
}
