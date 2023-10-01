package com.devpro.sinopsefs.controller;

import com.devpro.sinopsefs.dto.MidiaDTO;
import com.devpro.sinopsefs.hateoas.FilmeAssembler;
import com.devpro.sinopsefs.service.FilmeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/filme")
public class FilmeController {

    private final FilmeService filmeService;
    private final FilmeAssembler filmeAssembler;

    @PostMapping("/cadastrar")
    @ResponseStatus(code = HttpStatus.CREATED)
    public EntityModel<MidiaDTO> cadastarFilme(@RequestBody MidiaDTO midia) {
        return filmeAssembler.toModel(filmeService.salvarFilme(midia));
    }

    @GetMapping("/buscar/filmes")
    public CollectionModel<EntityModel<MidiaDTO>> buscarListaFilmes() {
        return filmeAssembler.toCollectionModel(filmeService.buscarListaFilmes());
    }

    @GetMapping("/nome")
    public CollectionModel<EntityModel<MidiaDTO>> buscarFilmesPorNome(@RequestParam String nome) {
        return filmeAssembler.toCollectionModel(filmeService.buscarFilmesPorNome(nome));
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
