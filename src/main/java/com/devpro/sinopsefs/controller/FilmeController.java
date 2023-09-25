package com.devpro.sinopsefs.controller;

import com.devpro.sinopsefs.model.Filme;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/filme")
public class FilmeController {

    @PostMapping
    public void cadastarFilme(@RequestBody Filme filme) {

    }

    @GetMapping
    public void buscarListaFilmes() {

    }

    @GetMapping
    public void buscarFilmePorNome(@RequestParam String nome) {

    }

    @DeleteMapping
    public void deletarFilme(@RequestParam Long id) {

    }
}
