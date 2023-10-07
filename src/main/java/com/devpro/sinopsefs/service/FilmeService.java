package com.devpro.sinopsefs.service;

import com.devpro.sinopsefs.dto.MidiaDTO;
import com.devpro.sinopsefs.exceptions.MidiaExistingException;
import com.devpro.sinopsefs.exceptions.MidiaNotFoundException;
import com.devpro.sinopsefs.model.Filme;
import com.devpro.sinopsefs.repository.FilmeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class FilmeService {

    private final FilmeRepository filmeRepository;

    private static final String FILME = "Filme";

    public MidiaDTO salvarFilme(MidiaDTO midia) {
        if (!filmeRepository.buscarFilmesPorNome(midia.getNome(), null).isEmpty()) {
            throw new MidiaExistingException(FILME, midia.getNome());
        }
        Filme filme = filmeRepository.save(new Filme(midia));
        return new MidiaDTO(filme);
    }

    public Page<MidiaDTO> buscarListaFilmes(Pageable pageable) {
        Page<Filme> filmes = filmeRepository.findAll(pageable);
        if (filmes.isEmpty()) {
            throw new MidiaNotFoundException(FILME);
        }
        Page<MidiaDTO> midias = filmes.map(filme -> {
            MidiaDTO midia = new MidiaDTO();
            midia.setId(filme.getId());
            midia.setNome(filme.getNome());
            midia.setSinopse(filme.getSinopse());
            midia.setGenero(filme.getGenero());
            return midia;
        });
        return midias;
    }

    public Page<MidiaDTO> buscarFilmesPorNome(String nome, Pageable pageable) {
        Page<Filme> filmes = filmeRepository.buscarFilmesPorNome(nome, pageable);
        if (filmes.isEmpty()) {
            throw new MidiaNotFoundException(FILME, nome);
        }
        Page<MidiaDTO> midias = filmes.map(filme -> {
            MidiaDTO midia = new MidiaDTO();
            midia.setId(filme.getId());
            midia.setNome(filme.getNome());
            midia.setSinopse(filme.getSinopse());
            midia.setGenero(filme.getGenero());
            return midia;
        });
        return midias;
    }

    public MidiaDTO editarFilme(MidiaDTO midia) {
        Filme filme = filmeRepository.findById(midia.getId()).orElseThrow(() -> new MidiaNotFoundException(FILME, midia.getId()));
        filme.setNome(midia.getNome());
        filme.setGenero(midia.getGenero());
        filme.setSinopse(midia.getSinopse());
        filmeRepository.save(filme);
        return new MidiaDTO(filme);
    }

    public void deletarFilmePorId(Long id) {
        filmeRepository.findById(id).orElseThrow(() -> new MidiaNotFoundException(FILME, id));
        filmeRepository.deleteById(id);
    }
}
