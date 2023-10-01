package com.devpro.sinopsefs.controller;

import com.devpro.sinopsefs.dto.MidiaDTO;
import com.devpro.sinopsefs.service.FilmeService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@AllArgsConstructor
@RestController
@RequestMapping("/api/filme")
public class FilmeController {

    private final FilmeService filmeService;

    @PostMapping("/cadastrar")
    @ResponseStatus(code = HttpStatus.CREATED)
    public MidiaDTO cadastarFilme(@RequestBody MidiaDTO midia) {
        MidiaDTO filme = filmeService.salvarFilme(midia);
        Link editarLink = linkTo(methodOn(FilmeController.class)
                .editarFilme(null))
                .withSelfRel()
                .withType("PUT");

        Link deletarLink = linkTo(methodOn(FilmeController.class)
                .deletarFilmePorId(filme.getId()))
                .withSelfRel()
                .withType("DELETE");

        filme.add(editarLink, deletarLink);
        return filme;
    }

    @GetMapping
    public List<MidiaDTO> buscarListaFilmes() {
        List<MidiaDTO> midias = filmeService.buscarListaFilmes();
        midias.forEach(midia -> {
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

            midia.add(selfLink, editarLink, deletarLink);
        });
        return midias;
    }

    @GetMapping("/nome")
    public List<MidiaDTO> buscarFilmesPorNome(@RequestParam String nome) {
        List<MidiaDTO> midias = filmeService.buscarFilmesPorNome(nome);
        midias.forEach(midia -> {
            Link editarLink = linkTo(methodOn(FilmeController.class)
                    .editarFilme(null))
                    .withSelfRel()
                    .withType("PUT");

            Link deletarLink = linkTo(methodOn(FilmeController.class)
                    .deletarFilmePorId(midia.getId()))
                    .withSelfRel()
                    .withType("DELETE");

            midia.add(editarLink, deletarLink);
        });
        return midias;
    }

    @PutMapping("/editar")
    public MidiaDTO editarFilme(@RequestBody MidiaDTO midia) {
        MidiaDTO filme = filmeService.editarFilme(midia);
        Link selfLink = linkTo(methodOn(SerieController.class)
                .buscarSeriePorNome(filme.getNome()))
                .withSelfRel()
                .withType("GET");

        Link deletarLink = linkTo(methodOn(FilmeController.class)
                .deletarFilmePorId(filme.getId()))
                .withSelfRel()
                .withType("DELETE");

        filme.add(selfLink, deletarLink);
        return filme;
    }

    @DeleteMapping("/deletar")
    public ResponseEntity<?> deletarFilmePorId(@RequestParam Long id) {
        filmeService.deletarFilmePorId(id);
        return ResponseEntity.noContent().build();
    }
}
