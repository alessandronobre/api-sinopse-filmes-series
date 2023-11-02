package com.devpro.sinopsefs.service;

import com.devpro.sinopsefs.dto.MidiaDTO;
import com.devpro.sinopsefs.exceptions.ItemExistenteException;
import com.devpro.sinopsefs.exceptions.ItemNotFoundException;
import com.devpro.sinopsefs.mapper.MidiaMapper;
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

    private final MidiaMapper midiaMapper;

    private static final String SERIE = "Serie";

    public MidiaDTO salvarSerie(MidiaDTO midia) {
        if (!serieRepository.buscarSeriesPorNome(midia.getNome(), null).isEmpty()) {
            throw new ItemExistenteException(SERIE, midia.getNome());
        }
        Serie serie = serieRepository.save(midiaMapper.converteMidiaDtoParaSerieEntidade(midia));
        return midiaMapper.converteSerieEntidadeParaMidiaDto(serie);
    }

    public Page<MidiaDTO> buscarListaSeries(Pageable pageable) {
        Page<Serie> series = serieRepository.findAll(pageable);
        if (series.isEmpty()) {
            throw new ItemNotFoundException(SERIE);
        }
        Page<MidiaDTO> midias = series.map(serie -> midiaMapper.converteSerieEntidadeParaMidiaDto(serie));
        return midias;
    }

    public Page<MidiaDTO> buscarSeriesPorNome(String nome, Pageable pageable) {
        Page<Serie> series = serieRepository.buscarSeriesPorNome(nome, pageable);
        if (series.isEmpty()) {
            throw new ItemNotFoundException(SERIE, nome);
        }
        Page<MidiaDTO> midias = series.map(serie -> midiaMapper.converteSerieEntidadeParaMidiaDto(serie));
        return midias;
    }

    public MidiaDTO editarSerie(MidiaDTO midia) {
        Serie serie = serieRepository.findById(midia.getId()).orElseThrow(() -> new ItemNotFoundException(SERIE, midia.getId()));
        serie.setNome(midia.getNome());
        serie.setGenero(midia.getGenero());
        serie.setSinopse(midia.getSinopse());
        serieRepository.save(serie);
        return midiaMapper.converteSerieEntidadeParaMidiaDto(serie);
    }

    public void deletarSeriePorId(Long id) {
        serieRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(SERIE, id));
        serieRepository.deleteById(id);
    }
}
