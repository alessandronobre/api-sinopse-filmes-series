package com.devpro.sinopsefs.service;

import com.devpro.sinopsefs.dto.MidiaDTO;
import com.devpro.sinopsefs.exceptions.SerieNotFoundException;
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
        if (series.isEmpty()) {
            throw new SerieNotFoundException();
        }
        series.stream().forEach(serie -> midias.add(new MidiaDTO(serie)));
        return midias;
    }

    public List<MidiaDTO> buscarSeriesPorNome(String nome) {
        List<Serie> series = serieRepository.buscarSeriesPorNome(nome);
        List<MidiaDTO> midias = new ArrayList<>();
        if (series.isEmpty()) {
            throw new SerieNotFoundException(nome);
        }
        series.stream().forEach(serie -> midias.add(new MidiaDTO(serie)));
        return midias;
    }

    public void editarSerie(MidiaDTO midia) {
        Serie serie = serieRepository.findById(midia.getId()).orElseThrow(() -> new SerieNotFoundException(midia.getId()));
        serie.setNome(midia.getNome());
        serie.setGenero(midia.getGenero());
        serie.setSinopse(midia.getSinopse());
        serieRepository.save(serie);
    }

    public void deletarSeriePorId(Long id) {
        serieRepository.findById(id).orElseThrow(() -> new SerieNotFoundException(id));
        serieRepository.deleteById(id);

    }
}
