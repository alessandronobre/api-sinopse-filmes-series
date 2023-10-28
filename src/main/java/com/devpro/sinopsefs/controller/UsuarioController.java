package com.devpro.sinopsefs.controller;

import com.devpro.sinopsefs.config.security.TokenDTO;
import com.devpro.sinopsefs.config.security.TokenService;
import com.devpro.sinopsefs.dto.UsuarioDTO;
import com.devpro.sinopsefs.hateoas.UsuarioAssembler;
import com.devpro.sinopsefs.model.Usuario;
import com.devpro.sinopsefs.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    private AuthenticationManager authenticationManager;
    private TokenService tokenService;
    private UsuarioService usuarioService;
    private UsuarioAssembler usuarioAssembler;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid Usuario usuario) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(usuario.getLogin(), usuario.getSenha());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        String tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok().body(new TokenDTO(tokenJWT));
    }

    @PostMapping("/cadastro")
    public ResponseEntity cadastrar(@RequestBody @Valid UsuarioDTO usuario) {
        usuarioService.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/editar")
    public EntityModel<UsuarioDTO> editarUsuario(@RequestBody @Valid UsuarioDTO usuario) {
        return usuarioAssembler.toModel(usuarioService.editarUsuario(usuario));
    }

    @DeleteMapping("/deletar")
    public ResponseEntity deletarUsuarioPorId(@RequestParam Long id) {
        usuarioService.deletarUsuarioPorId(id);
        return ResponseEntity.noContent().build();
    }
}
