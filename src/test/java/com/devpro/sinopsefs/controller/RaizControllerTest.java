package com.devpro.sinopsefs.controller;

import com.devpro.sinopsefs.hateoas.RaizModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.hateoas.Link;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class RaizControllerTest {

    @InjectMocks
    private RaizController raizControllerMock;

    @Test
    void quandoRaizForChamadoDeveRetornarListaDeLinks() {
        Link linkFilme = Link.of("/api/filme/buscar/filmes").withRel("filmes").withType("GET");
        Link linkSerie = Link.of("/api/serie/buscar/series").withRel("series").withType("GET");

        RaizModel raizModel = raizControllerMock.raiz();

        assertTrue(raizModel.getLinks().toList().get(0).toString().equals(linkFilme.toString()));
        assertTrue(raizModel.getLinks().toList().get(1).toString().equals(linkSerie.toString()));
    }
}
