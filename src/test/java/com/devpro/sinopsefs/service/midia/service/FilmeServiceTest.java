package com.devpro.sinopsefs.service.midia.service;

import com.devpro.sinopsefs.dto.MidiaDTO;
import com.devpro.sinopsefs.exceptions.ItemExistenteException;
import com.devpro.sinopsefs.exceptions.ItemNotFoundException;
import com.devpro.sinopsefs.mapper.MidiaMapper;
import com.devpro.sinopsefs.model.Filme;
import com.devpro.sinopsefs.repository.FilmeRepository;
import com.devpro.sinopsefs.service.FilmeService;
import com.devpro.sinopsefs.service.midia.builders.FilmeBuilder;
import com.devpro.sinopsefs.service.midia.builders.MidiaDtoBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FilmeServiceTest {

    @InjectMocks
    private FilmeService filmeServiceMock;

    @Mock
    private FilmeRepository filmeRepositoryMock;

    @Mock
    private MidiaMapper midiaMapperMock;

    private static final Long ID = 810L;

    @Test
    void quandoSalvarFilmeForChamadoPassandoMidiaValidoDeveRetornarMidiaDto() {
        MidiaDTO midia = MidiaDtoBuilder.builder().build();
        when(filmeRepositoryMock.buscarFilmesPorNome(any(), any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        when(filmeRepositoryMock.save(any())).thenReturn(null);
        when(midiaMapperMock.converteFilmeEntidadeParaDto(any())).thenReturn(midia);

        MidiaDTO result = filmeServiceMock.salvarFilme(midia);

        assertEquals(result.getId(), midia.getId());
        verify(filmeRepositoryMock, times(1)).buscarFilmesPorNome(any(), any());
        verify(filmeRepositoryMock, times(1)).save(any());
        verify(midiaMapperMock, times(1)).converteFilmeEntidadeParaDto(any());
    }

    @Test
    void quandoSalvarFilmeForChamadoPassandoMidiaInvalidaDeveRetornarItemExistenteException() {
        when(filmeRepositoryMock.buscarFilmesPorNome(any(), any())).thenReturn(instanciaPageFilme());
        String msgEsperada = "Já existe Filme com o nome matrix cadastrado";

        ItemExistenteException exception = assertThrows(ItemExistenteException.class, () -> filmeServiceMock
                .salvarFilme(MidiaDtoBuilder.builder().build()));

        assertEquals(msgEsperada, exception.getMessage());
        verify(filmeRepositoryMock, times(1)).buscarFilmesPorNome(any(), any());
    }

    @Test
    void quandoBuscarListaFilmesForChamadoComRetornoDeFindAllValidoDeveRetornarPageValido() {
        Pageable pageable = instanciaPageable();
        when(filmeRepositoryMock.findAll(pageable)).thenReturn(instanciaPageFilme());

        Page<MidiaDTO> result = filmeServiceMock.buscarListaFilmes(pageable);

        assertEquals(result.getTotalPages(), 1);
        verify(filmeRepositoryMock, times(1)).findAll(pageable);
    }

    @Test
    void quandoBuscarListaFilmesForChamadoComRetornoDeFindAllVazioDeveRetornarItemNotFoundException() {
        Pageable pageable = instanciaPageable();
        when(filmeRepositoryMock.findAll(pageable)).thenReturn(new PageImpl<>(new ArrayList<>()));
        String msgEsperada = "Nenhum(a) Filme cadastrado na base";

        ItemNotFoundException exception = assertThrows(ItemNotFoundException.class, () -> filmeServiceMock
                .buscarListaFilmes(instanciaPageable()));

        assertEquals(msgEsperada, exception.getMessage());
        verify(filmeRepositoryMock, times(1)).findAll(pageable);
    }

    @Test
    void quandoBuscarFilmesPorNomeForChamadoPassandoFilmeValidoDeveRetornarPageValido() {
        MidiaDTO midia = MidiaDtoBuilder.builder().build();
        when(filmeRepositoryMock.buscarFilmesPorNome(any(), any())).thenReturn(instanciaPageFilme());
        when(midiaMapperMock.converteFilmeEntidadeParaDto(any())).thenReturn(midia);

        Page<MidiaDTO> result = filmeServiceMock.buscarFilmesPorNome("", instanciaPageable());

        assertEquals(result.getTotalPages(), 1);
        verify(filmeRepositoryMock, times(1)).buscarFilmesPorNome(any(), any());
        verify(midiaMapperMock, times(1)).converteFilmeEntidadeParaDto(any());
    }

    @Test
    void quandoBuscarFilmesPorNomeForChamadoPassandoFilmeInvalidoDeveRetornarItemNotFoundException() {
        when(filmeRepositoryMock.buscarFilmesPorNome(any(), any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        String msgEsperada = "Filme madMax não encontrado";

        ItemNotFoundException exception = assertThrows(ItemNotFoundException.class, () -> filmeServiceMock
                .buscarFilmesPorNome("madMax", instanciaPageable()));

        assertEquals(msgEsperada, exception.getMessage());
        verify(filmeRepositoryMock, times(1)).buscarFilmesPorNome(any(), any());
    }

    @Test
    void quandoEditarFilmeForChamadoPassandoMidiaValidoDeveRetornarMidiaDto() {
        MidiaDTO midia = MidiaDtoBuilder.builder().build();
        when(filmeRepositoryMock.findById(any())).thenReturn(Optional.of(new Filme()));
        when(filmeRepositoryMock.save(any())).thenReturn(null);
        when(midiaMapperMock.converteFilmeEntidadeParaDto(any())).thenReturn(midia);

        MidiaDTO result = filmeServiceMock.editarFilme(midia);

        assertEquals(result.getId(), midia.getId());
        verify(filmeRepositoryMock, times(1)).findById(any());
        verify(filmeRepositoryMock, times(1)).save(any());
        verify(midiaMapperMock, times(1)).converteFilmeEntidadeParaDto(any());
    }

    @Test
    void quandoEditarFilmeForChamadoPassandoMidiaInvalidaDeveRetornarItemNotFoundException() {
        when(filmeRepositoryMock.findById(any())).thenReturn(Optional.ofNullable(null));
        String msgEsperada = "Filme de id 810 não encontrado";

        ItemNotFoundException exception = assertThrows(ItemNotFoundException.class, () -> filmeServiceMock
                .editarFilme(MidiaDtoBuilder.builder().build()));

        assertEquals(msgEsperada, exception.getMessage());
        verify(filmeRepositoryMock, times(1)).findById(any());
    }

    @Test
    void quandoDeletarFilmePorIdForChamadoPassandoIdValidoDeveDeletarFilme() {
        Filme filme = FilmeBuilder.builder().build();
        when(filmeRepositoryMock.findById(ID)).thenReturn((Optional.ofNullable(filme)));
        doNothing().when(filmeRepositoryMock).deleteById(ID);

        filmeServiceMock.deletarFilmePorId(ID);

        verify(filmeRepositoryMock, times(1)).findById(ID);
        verify(filmeRepositoryMock, times(1)).deleteById(ID);
    }

    @Test
    void quandoDeletarFilmePorIdForChamadoPassandoIdInvalidoDeveRetornarItemNotFoundException() {
        when(filmeRepositoryMock.findById(ID)).thenReturn(Optional.ofNullable(null));
        String msgEsperada = "Filme de id 810 não encontrado";

        ItemNotFoundException exception = assertThrows(ItemNotFoundException.class, () -> filmeServiceMock
                .deletarFilmePorId(ID));

        assertEquals(msgEsperada, exception.getMessage());
        verify(filmeRepositoryMock, times(1)).findById(ID);
    }

    private static Page<Filme> instanciaPageFilme() {
        List<Filme> filmeList = new ArrayList<>();
        filmeList.add(new Filme());
        Page<Filme> paginaFilme = new PageImpl<>(filmeList);
        return paginaFilme;
    }

    private static Pageable instanciaPageable() {
        return PageRequest.of(2, 5);
    }
}
