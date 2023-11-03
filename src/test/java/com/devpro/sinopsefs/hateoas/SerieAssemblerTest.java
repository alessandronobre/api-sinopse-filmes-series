package com.devpro.sinopsefs.hateoas;

import com.devpro.sinopsefs.dto.MidiaDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SerieAssemblerTest {

    private SerieAssembler serieAssembler;

    @BeforeEach
    public void setUp() {
        serieAssembler = new SerieAssembler();
    }

    @Test
    public void testAddLinksToSingleResource() {
        EntityModel<MidiaDTO> resource = EntityModel.of(new MidiaDTO());
        serieAssembler.addLinks(resource);

        Link selfLink = Link.of("/api/serie/nome?nome={nome}").withRel("self").withType("GET");
        Link editarLink = Link.of("/api/serie/editar").withRel("self").withType("PUT");
        Link deletarLink = Link.of("/api/serie/deletar?id={id}").withRel("self").withType("DELETE");

        assertTrue(resource.getLinks().toList().get(0).toString().equals(selfLink.toString()));
        assertTrue(resource.getLinks().toList().get(1).toString().equals(editarLink.toString()));
        assertTrue(resource.getLinks().toList().get(2).toString().equals(deletarLink.toString()));
    }

    @Test
    public void testAddLinksToCollection() {
        CollectionModel<EntityModel<MidiaDTO>> resources = CollectionModel.empty();
        serieAssembler.addLinks(resources);

        Link selfLink = Link.of("/api/serie/buscar/series").withRel("self").withType("GET");
        Link cadastroLink = Link.of("/api/serie/cadastrar").withRel("self").withType("POST");

        assertTrue(resources.getLinks().toList().get(0).toString().equals(selfLink.toString()));
        assertTrue(resources.getLinks().toList().get(1).toString().equals(cadastroLink.toString()));
    }
}
