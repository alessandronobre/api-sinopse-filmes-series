package com.devpro.sinopsefs.controller;

import com.devpro.sinopsefs.dto.MidiaDTO;
import com.devpro.sinopsefs.hateoas.SerieAssembler;
import com.devpro.sinopsefs.service.SerieService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
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
    private PagedResourcesAssembler<MidiaDTO> pagedResourcesAssembler;

    @PostMapping("/cadastrar")
    @ResponseStatus(code = HttpStatus.CREATED)
    public EntityModel<MidiaDTO> cadastarSerie(@RequestBody MidiaDTO midia) {
        return serieAssembler.toModel(serieService.salvarSerie(midia));
    }

    @GetMapping("/buscar/series")
    public CollectionModel<EntityModel<MidiaDTO>> buscarListaSeries(@PageableDefault(size=5) Pageable pageable) {
        Page<MidiaDTO> midias = serieService.buscarListaSeries(pageable);
        return pagedResourcesAssembler.toModel(midias, serieAssembler);
    }

    @GetMapping("/nome")
    public CollectionModel<EntityModel<MidiaDTO>> buscarSeriePorNome(@RequestParam String nome,
                                                                     @PageableDefault(size=5) Pageable pageable) {
        Page<MidiaDTO> midias = serieService.buscarSeriesPorNome(nome, pageable);
        return pagedResourcesAssembler.toModel(midias, serieAssembler);
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
