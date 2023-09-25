package com.devpro.sinopsefs.controller;

import com.devpro.sinopsefs.dto.MidiaDTO;
import com.devpro.sinopsefs.service.SerieService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/serie")
public class SerieController {

    private final SerieService serieService;

    @PostMapping
    public void cadastarSerie(@RequestBody MidiaDTO serie) {
        serieService.salvarSerie(serie);
    }

    @GetMapping
    public List<MidiaDTO> buscarListaSeries() {
        return serieService.buscarListaSeries();
    }

    @GetMapping("/nome")
    public void buscarSeriePorNome(@RequestParam String nome) {

    }

    @DeleteMapping
    public void deletarSerie(@RequestParam Long id) {

    }
}
