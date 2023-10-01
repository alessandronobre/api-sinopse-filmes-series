package com.devpro.sinopsefs.hateoas;

import com.devpro.sinopsefs.controller.SerieController;
import com.devpro.sinopsefs.dto.MidiaDTO;
import com.devpro.sinopsefs.model.Filme;
import com.devpro.sinopsefs.model.Midia;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class SerieAssembler implements SimpleRepresentationModelAssembler<MidiaDTO>  {

    @Override
    public void addLinks(EntityModel<MidiaDTO> resource) {
        MidiaDTO midia = resource.getContent();
        Link selfLink = linkTo(methodOn(SerieController.class)
                .buscarSeriePorNome(midia.getNome()))
                .withSelfRel()
                .withType("GET");

        Link editarLink = linkTo(methodOn(SerieController.class)
                .editarSerie(null))
                .withSelfRel()
                .withType("PUT");

        Link deletarLink = linkTo(methodOn(SerieController.class)
                .deletarSeriePorId(midia.getId()))
                .withSelfRel()
                .withType("DELETE");

        resource.add(selfLink, editarLink, deletarLink);
    }

    @Override
    public void addLinks(CollectionModel<EntityModel<MidiaDTO>> resources) {
        Link cadastroLink = linkTo(methodOn(SerieController.class)
                .cadastarSerie(null))
                .withSelfRel()
                .withType("POST");

        Link selfLink = linkTo(methodOn(SerieController.class)
                .buscarListaSeries())
                .withSelfRel()
                .withType("GET");

        resources.add(selfLink, cadastroLink);
    }
}
