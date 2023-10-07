package com.devpro.sinopsefs.service;

import com.devpro.sinopsefs.dto.MidiaDTO;
import com.devpro.sinopsefs.exceptions.MidiaExistingException;
import com.devpro.sinopsefs.exceptions.MidiaNotFoundException;
import com.devpro.sinopsefs.model.Serie;
import com.devpro.sinopsefs.repository.SerieRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class SerieService {

    private final SerieRepository serieRepository;

    private static final String SERIE = "Serie";

    public MidiaDTO salvarSerie(MidiaDTO midia) {
        if (!serieRepository.buscarSeriesPorNome(midia.getNome(), null).isEmpty()) {
            throw new MidiaExistingException(SERIE, midia.getNome());
        }
        Serie serie = serieRepository.save(new Serie(midia));
        return new MidiaDTO(serie);
    }

    public Page<MidiaDTO> buscarListaSeries(Pageable pageable) {
        Page<Serie> series = serieRepository.findAll(pageable);
        if (series.isEmpty()) {
            throw new MidiaNotFoundException(SERIE);
        }
        Page<MidiaDTO> midias = series.map(serie -> {
            MidiaDTO midia = new MidiaDTO();
            midia.setId(serie.getId());
            midia.setNome(serie.getNome());
            midia.setSinopse(serie.getSinopse());
            midia.setGenero(serie.getGenero());
            return midia;
        });
        return midias;
    }

    public Page<MidiaDTO> buscarSeriesPorNome(String nome, Pageable pageable) {
        Page<Serie> series = serieRepository.buscarSeriesPorNome(nome, pageable);
        if (series.isEmpty()) {
            throw new MidiaNotFoundException(SERIE, nome);
        }
        Page<MidiaDTO> midias = series.map(serie -> {
            MidiaDTO midia = new MidiaDTO();
            midia.setId(serie.getId());
            midia.setNome(serie.getNome());
            midia.setSinopse(serie.getSinopse());
            midia.setGenero(serie.getGenero());
            return midia;
        });
        return midias;
    }

    public MidiaDTO editarSerie(MidiaDTO midia) {
        Serie serie = serieRepository.findById(midia.getId()).orElseThrow(() -> new MidiaNotFoundException(SERIE, midia.getId()));
        serie.setNome(midia.getNome());
        serie.setGenero(midia.getGenero());
        serie.setSinopse(midia.getSinopse());
        serieRepository.save(serie);
        return new MidiaDTO(serie);
    }

    public void deletarSeriePorId(Long id) {
        serieRepository.findById(id).orElseThrow(() -> new MidiaNotFoundException(SERIE, id));
        serieRepository.deleteById(id);

    }
}
