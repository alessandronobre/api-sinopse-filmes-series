package com.devpro.sinopsefs.controller;

import com.devpro.sinopsefs.dto.MidiaDTO;
import com.devpro.sinopsefs.hateoas.SerieAssembler;
import com.devpro.sinopsefs.service.SerieService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/serie")
public class SerieController {

    private final SerieService serieService;
    private final SerieAssembler serieAssembler;
    @PostMapping("/cadastrar")
    @ResponseStatus(code = HttpStatus.CREATED)
    public EntityModel<MidiaDTO> cadastarSerie(@RequestBody MidiaDTO midia) {
        return serieAssembler.toModel(serieService.salvarSerie(midia));
    }

    @GetMapping("/buscar/series")
    public CollectionModel<EntityModel<MidiaDTO>> buscarListaSeries() {
        return serieAssembler.toCollectionModel(serieService.buscarListaSeries());
    }

    @GetMapping("/nome")
    public CollectionModel<EntityModel<MidiaDTO>> buscarSeriePorNome(@RequestParam String nome) {
        return serieAssembler.toCollectionModel(serieService.buscarSeriesPorNome(nome));
    }

    @PutMapping("/editar")
    public EntityModel<MidiaDTO> editarSerie(@RequestBody MidiaDTO midia) {
        return serieAssembler.toModel(serieService.editarSerie(midia));
    }

    @DeleteMapping("/deletar")
    public ResponseEntity<?> deletarSeriePorId(@RequestParam Long id) {
        serieService.deletarSeriePorId(id);
        return ResponseEntity.noContent().build();
    }
}
