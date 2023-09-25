package com.devpro.sinopsefs.controller;

import com.devpro.sinopsefs.model.Serie;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/serie")
public class SerieController {

    @PostMapping
    public void cadastarSerie(@RequestBody Serie serie) {

    }

    @GetMapping
    public void buscarListaSeries() {

    }

    @GetMapping
    public void buscarSeriePorNome(@RequestParam String nome) {

    }

    @DeleteMapping
    public void deletarSerie(@RequestParam Long id) {

    }
}
