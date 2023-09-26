package com.devpro.sinopsefs.service;

import com.devpro.sinopsefs.dto.MidiaDTO;
import com.devpro.sinopsefs.model.Serie;
import com.devpro.sinopsefs.repository.SerieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class SerieService {

    private final SerieRepository serieRepository;

    public void salvarSerie(MidiaDTO serie) {
        serieRepository.save(new Serie(serie));
    }

    public List<MidiaDTO> buscarListaSeries() {
        List<Serie> series = serieRepository.findAll();
        List<MidiaDTO> midias = new ArrayList<>();
        if (!series.isEmpty()) {
            series.stream().forEach(serie -> midias.add(new MidiaDTO(serie)));
            return midias;
        }
        return null;
    }

    public List<MidiaDTO> buscarSeriesPorNome(String nome) {
        List<Serie> series = serieRepository.buscarSeriesPorNome(nome);
        List<MidiaDTO> midias = new ArrayList<>();
        if (!series.isEmpty()) {
            series.stream().forEach(filme -> midias.add(new MidiaDTO(filme)));
            return midias;
        }
        return null;
    }

    public void deletarSeriePorId(Long id) {
        serieRepository.deleteById(id);

    }
}
