package com.devpro.sinopsefs.controller;

import com.devpro.sinopsefs.dto.MidiaDTO;
import com.devpro.sinopsefs.hateoas.FilmeAssembler;
import com.devpro.sinopsefs.service.FilmeService;
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
@RequestMapping("/api/filme")
public class FilmeController {

    private final FilmeService filmeService;
    private final FilmeAssembler filmeAssembler;
    private PagedResourcesAssembler<MidiaDTO> pagedResourcesAssembler;

    @PostMapping("/cadastrar")
    @ResponseStatus(code = HttpStatus.CREATED)
    public EntityModel<MidiaDTO> cadastarFilme(@RequestBody MidiaDTO midia) {
        return filmeAssembler.toModel(filmeService.salvarFilme(midia));
    }

    @GetMapping("/buscar/filmes")
    public CollectionModel<EntityModel<MidiaDTO>> buscarListaFilmes(@PageableDefault(size=5) Pageable pageable) {
        Page<MidiaDTO> midias = filmeService.buscarListaFilmes(pageable);
        return pagedResourcesAssembler.toModel(midias, filmeAssembler);
    }

    @GetMapping("/nome")
    public CollectionModel<EntityModel<MidiaDTO>> buscarFilmesPorNome(@RequestParam String nome,
                                                                      @PageableDefault(size=5) Pageable pageable) {
        Page<MidiaDTO> midias = filmeService.buscarFilmesPorNome(nome, pageable);
        return pagedResourcesAssembler.toModel(midias, filmeAssembler);
    }

    @PutMapping("/editar")
    public EntityModel<MidiaDTO> editarFilme(@RequestBody MidiaDTO midia) {
        return filmeAssembler.toModel(filmeService.editarFilme(midia));
    }

    @DeleteMapping("/deletar")
    public ResponseEntity<?> deletarFilmePorId(@RequestParam Long id) {
        filmeService.deletarFilmePorId(id);
        return ResponseEntity.noContent().build();
    }
}
