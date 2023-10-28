package com.devpro.sinopsefs.hateoas;

import com.devpro.sinopsefs.controller.UsuarioController;
import com.devpro.sinopsefs.dto.UsuarioDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UsuarioAssembler implements SimpleRepresentationModelAssembler<UsuarioDTO> {
    @Override
    public void addLinks(EntityModel<UsuarioDTO> resource) {
        UsuarioDTO usuario = resource.getContent();
        Link editarLink = linkTo(methodOn(UsuarioController.class)
                .editarUsuario(null))
                .withSelfRel()
                .withType("PUT");

        Link deletarLink = linkTo(methodOn(UsuarioController.class)
                .deletarUsuarioPorId(usuario.getId()))
                .withSelfRel()
                .withType("DELETE");

        resource.add(editarLink, deletarLink);
    }

    @Override
    public void addLinks(CollectionModel<EntityModel<UsuarioDTO>> resources) {
        Link cadastroLink = linkTo(methodOn(UsuarioController.class)
                .cadastrar(null))
                .withSelfRel()
                .withType("POST");

        Link loginLink = linkTo(methodOn(UsuarioController.class)
                .login(null))
                .withSelfRel()
                .withType("POST");

        resources.add(loginLink, cadastroLink);
    }
}
