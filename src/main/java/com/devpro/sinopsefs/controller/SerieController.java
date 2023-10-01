package com.devpro.sinopsefs.controller;

import com.devpro.sinopsefs.dto.MidiaDTO;
import com.devpro.sinopsefs.service.SerieService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/serie")
public class SerieController {

    private final SerieService serieService;

    @PostMapping("/cadastrar")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void cadastarSerie(@RequestBody MidiaDTO serie) {
        serieService.salvarSerie(serie);

    }

    @GetMapping
    public List<MidiaDTO> buscarListaSeries() {
        return serieService.buscarListaSeries();

    }

    @GetMapping("/nome")
    public List<MidiaDTO> buscarSeriePorNome(@RequestParam String nome) {
        return serieService.buscarSeriesPorNome(nome);

    }

    @PutMapping("/editar")
    public void editarSerie(@RequestBody MidiaDTO filme) {
        serieService.editarSerie(filme);
    }

    @DeleteMapping("/deletar")
    public void deletarSeriePorId(@RequestParam Long id) {
        serieService.deletarSeriePorId(id);

    }
}
