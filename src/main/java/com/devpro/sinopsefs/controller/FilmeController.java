package com.devpro.sinopsefs.controller;

import com.devpro.sinopsefs.dto.MidiaDTO;
import com.devpro.sinopsefs.service.FilmeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/filme")
public class FilmeController {

    private final FilmeService filmeService;

    @PostMapping
    public void cadastarFilme(@RequestBody MidiaDTO filme) {
        filmeService.salvarFilme(filme);
    }

    @GetMapping
    public void buscarListaFilmes() {

    }

    @GetMapping("/nome")
    public void buscarFilmePorNome(@RequestParam String nome) {

    }

    @DeleteMapping
    public void deletarFilme(@RequestParam Long id) {

    }
}
