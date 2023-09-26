package com.devpro.sinopsefs.service;

import com.devpro.sinopsefs.dto.MidiaDTO;
import com.devpro.sinopsefs.model.Filme;
import com.devpro.sinopsefs.repository.FilmeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class FilmeService {

    private final FilmeRepository filmeRepository;

    public void salvarFilme(MidiaDTO filme) {
        filmeRepository.save(new Filme(filme));
    }

    public List<MidiaDTO> buscarListaFilmes() {
        List<Filme> filmes = filmeRepository.findAll();
        List<MidiaDTO> midias = new ArrayList<>();
        if (!filmes.isEmpty()) {
            filmes.stream().forEach(filme -> midias.add(new MidiaDTO(filme)));
            return midias;
        }
        return null;
    }

    public List<MidiaDTO> buscarFilmesPorNome(String nome) {
        List<Filme> filmes = filmeRepository.buscarFilmesPorNome(nome);
        List<MidiaDTO> midias = new ArrayList<>();
        if (!filmes.isEmpty()) {
            filmes.stream().forEach(filme -> midias.add(new MidiaDTO(filme)));
            return midias;
        }
        return null;
    }
}
