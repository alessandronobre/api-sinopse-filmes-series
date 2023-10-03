package com.devpro.sinopsefs.controller;

import com.devpro.sinopsefs.hateoas.RaizModel;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api")
public class RaizController {

    @GetMapping
    public RaizModel raiz() {
        RaizModel raiz = new RaizModel();
        Link filmesLink = linkTo(methodOn(FilmeController.class)
                .buscarListaFilmes(null))
                .withRel("filmes")
                .withType("GET");

        Link seriesLink = linkTo(methodOn(SerieController.class)
                .buscarListaSeries(null))
                .withRel("series")
                .withType("GET");

        raiz.add(filmesLink, seriesLink);
        return raiz;
    }
}