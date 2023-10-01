package com.devpro.sinopsefs.hateoas;

import com.devpro.sinopsefs.controller.FilmeController;
import com.devpro.sinopsefs.dto.MidiaDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class FilmeAssembler implements SimpleRepresentationModelAssembler<MidiaDTO> {

    @Override
    public void addLinks(EntityModel<MidiaDTO> resource) {
        MidiaDTO midia = resource.getContent();
        Link selfLink = linkTo(methodOn(FilmeController.class)
                .buscarFilmesPorNome(midia.getNome()))
                .withSelfRel()
                .withType("GET");

        Link editarLink = linkTo(methodOn(FilmeController.class)
                .editarFilme(null))
                .withSelfRel()
                .withType("PUT");

        Link deletarLink = linkTo(methodOn(FilmeController.class)
                .deletarFilmePorId(midia.getId()))
                .withSelfRel()
                .withType("DELETE");

        resource.add(selfLink, editarLink, deletarLink);
    }

    @Override
    public void addLinks(CollectionModel<EntityModel<MidiaDTO>> resources) {
        Link cadastroLink = linkTo(methodOn(FilmeController.class)
                .cadastarFilme(null))
                .withSelfRel()
                .withType("POST");

        Link selfLink = linkTo(methodOn(FilmeController.class)
                .buscarListaFilmes())
                .withSelfRel()
                .withType("GET");

        resources.add(selfLink, cadastroLink);
    }
}
