package com.devpro.sinopsefs.service;

import com.devpro.sinopsefs.dto.MidiaDTO;
import com.devpro.sinopsefs.exceptions.ItemExistenteException;
import com.devpro.sinopsefs.exceptions.ItemNotFoundException;
import com.devpro.sinopsefs.mapper.MidiaMapper;
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

    private final MidiaMapper midiaMapper;

    private static final String FILME = "Filme";

    public MidiaDTO salvarFilme(MidiaDTO midia) {
        if (!filmeRepository.buscarFilmesPorNome(midia.getNome(), null).isEmpty()) {
            throw new ItemExistenteException(FILME, midia.getNome());
        }
        Filme filme = filmeRepository.save(midiaMapper.converteMidiaDtoParaFilmeEntidade(midia));
        return midiaMapper.converteFilmeEntidadeParaMidiaDto(filme);
    }

    public Page<MidiaDTO> buscarListaFilmes(Pageable pageable) {
        Page<Filme> filmes = filmeRepository.findAll(pageable);
        if (filmes.isEmpty()) {
            throw new ItemNotFoundException(FILME);
        }
        Page<MidiaDTO> midias = filmes.map(filme -> midiaMapper.converteFilmeEntidadeParaMidiaDto(filme));
        return midias;
    }

    public Page<MidiaDTO> buscarFilmesPorNome(String nome, Pageable pageable) {
        Page<Filme> filmes = filmeRepository.buscarFilmesPorNome(nome, pageable);
        if (filmes.isEmpty()) {
            throw new ItemNotFoundException(FILME, nome);
        }
        Page<MidiaDTO> midias = filmes.map(filme -> midiaMapper.converteFilmeEntidadeParaMidiaDto(filme));
        return midias;
    }

    public MidiaDTO editarFilme(MidiaDTO midia) {
        Filme filme = filmeRepository.findById(midia.getId()).orElseThrow(() -> new ItemNotFoundException(FILME, midia.getId()));
        filme.setNome(midia.getNome());
        filme.setGenero(midia.getGenero());
        filme.setSinopse(midia.getSinopse());
        filmeRepository.save(filme);
        return midiaMapper.converteFilmeEntidadeParaMidiaDto(filme);
    }

    public void deletarFilmePorId(Long id) {
        filmeRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(FILME, id));
        filmeRepository.deleteById(id);
    }
}
