package com.devpro.sinopsefs.hateoas;

import com.devpro.sinopsefs.controller.UsuarioController;
import com.devpro.sinopsefs.dto.UsuarioDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

public class UsuarioAssemblerTest {

    private UsuarioAssembler usuarioAssembler;

    @BeforeEach
    public void setUp() {
        usuarioAssembler = new UsuarioAssembler();
    }

    @Test
    public void quandoaAdLinksForChamadoDeveRetornarLinks() {
        EntityModel<UsuarioDTO> resource = EntityModel.of(new UsuarioDTO());
        usuarioAssembler.addLinks(resource);

        Link editarLink = linkTo(UsuarioController.class).slash("editar").withSelfRel().withType("PUT");
        Link deleteLink = linkTo(UsuarioController.class).slash("deletar").slash("?id={id}").withSelfRel().withType("DELETE");

        assertTrue(resource.getLinks().toList().get(0).toString().equals(editarLink.toString()));
        assertTrue(resource.getLinks().toList().get(1).toString().equals(deleteLink.toString()));
    }

    @Test
    public void quandoaAdLinksForChamadoDeveRetornarCollectionLinks() {
        CollectionModel<EntityModel<UsuarioDTO>> resources = CollectionModel.empty();
        usuarioAssembler.addLinks(resources);

        Link loginLink = linkTo(UsuarioController.class).slash("login").withSelfRel().withType("POST");
        Link cadastroLink = linkTo(UsuarioController.class).slash("cadastro").withSelfRel().withType("POST");

        assertTrue(resources.getLinks().toList().get(0).toString().equals(loginLink.toString()));
        assertTrue(resources.getLinks().toList().get(1).toString().equals(cadastroLink.toString()));
    }
}
