package com.devpro.sinopsefs.controller;

import com.devpro.sinopsefs.dto.MidiaDTO;
import com.devpro.sinopsefs.service.SerieService;
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
@RequestMapping("/api/serie")
public class SerieController {

    private final SerieService serieService;

    @PostMapping("/cadastrar")
    @ResponseStatus(code = HttpStatus.CREATED)
    public MidiaDTO cadastarSerie(@RequestBody MidiaDTO midia) {
        MidiaDTO serie = serieService.salvarSerie(midia);
        Link editarLink = linkTo(methodOn(SerieController.class)
                .editarSerie(null))
                .withSelfRel()
                .withType("PUT");

        Link deletarLink = linkTo(methodOn(SerieController.class)
                .deletarSeriePorId(serie.getId()))
                .withSelfRel()
                .withType("DELETE");

        serie.add(editarLink, deletarLink);
        return serie;
    }

    @GetMapping
    public List<MidiaDTO> buscarListaSeries() {
        List<MidiaDTO> midias = serieService.buscarListaSeries();
        midias.forEach(midia -> {
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

            midia.add(selfLink, editarLink, deletarLink);
        });
        return midias;
    }

    @GetMapping("/nome")
    public List<MidiaDTO> buscarSeriePorNome(@RequestParam String nome) {
        List<MidiaDTO> midias = serieService.buscarSeriesPorNome(nome);
        midias.forEach(midia -> {
            Link editarLink = linkTo(methodOn(SerieController.class)
                    .editarSerie(null))
                    .withSelfRel()
                    .withType("PUT");

            Link deletarLink = linkTo(methodOn(SerieController.class)
                    .deletarSeriePorId(midia.getId()))
                    .withSelfRel()
                    .withType("DELETE");

            midia.add(editarLink, deletarLink);
        });
        return midias;
    }

    @PutMapping("/editar")
    public MidiaDTO editarSerie(@RequestBody MidiaDTO midia) {
        MidiaDTO serie = serieService.editarSerie(midia);
        Link selfLink = linkTo(methodOn(SerieController.class)
                .buscarSeriePorNome(serie.getNome()))
                .withSelfRel()
                .withType("GET");

        Link deletarLink = linkTo(methodOn(SerieController.class)
                .deletarSeriePorId(serie.getId()))
                .withSelfRel()
                .withType("DELETE");

        serie.add(selfLink, deletarLink);
        return serie;
    }

    @DeleteMapping("/deletar")
    public ResponseEntity<?> deletarSeriePorId(@RequestParam Long id) {
        serieService.deletarSeriePorId(id);
        return ResponseEntity.noContent().build();
    }
}
