package com.devpro.sinopsefs.service;

import com.devpro.sinopsefs.dto.MidiaDTO;
import com.devpro.sinopsefs.model.Serie;
import com.devpro.sinopsefs.repository.SerieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class SerieService {

    private final SerieRepository serieRepository;

    public void salvarSerie(MidiaDTO serie) {
        serieRepository.save(new Serie(serie));
    }
}
