package com.devpro.sinopsefs.service.midia.service;

import com.devpro.sinopsefs.dto.MidiaDTO;
import com.devpro.sinopsefs.exceptions.ItemExistenteException;
import com.devpro.sinopsefs.exceptions.ItemNotFoundException;
import com.devpro.sinopsefs.mapper.MidiaMapper;
import com.devpro.sinopsefs.model.Serie;
import com.devpro.sinopsefs.repository.SerieRepository;
import com.devpro.sinopsefs.service.SerieService;
import com.devpro.sinopsefs.service.midia.builders.MidiaDtoBuilder;
import com.devpro.sinopsefs.service.midia.builders.SerieBuilder;
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
public class SerieServiceTest {

    @InjectMocks
    private SerieService serieServiceMock;

    @Mock
    private SerieRepository serieRepositoryMock;

    @Mock
    private MidiaMapper midiaMapperMock;

    private static final Long ID = 810L;

    @Test
    void quandoSalvarSerieForChamadoPassandoMidiaValidoDeveRetornarMidiaDto() {
        MidiaDTO midia = MidiaDtoBuilder.builder().build();
        when(serieRepositoryMock.buscarSeriesPorNome(any(), any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        when(serieRepositoryMock.save(any())).thenReturn(null);
        when(midiaMapperMock.converteSerieEntidadeParaDto(any())).thenReturn(midia);

        MidiaDTO result = serieServiceMock.salvarSerie(midia);

        assertEquals(result.getId(), midia.getId());
        verify(serieRepositoryMock, times(1)).buscarSeriesPorNome(any(), any());
        verify(serieRepositoryMock, times(1)).save(any());
        verify(midiaMapperMock, times(1)).converteSerieEntidadeParaDto(any());
    }

    @Test
    void quandoSalvarSerieForChamadoPassandoMidiaInvalidaDeveRetornarItemExistenteException() {
        when(serieRepositoryMock.buscarSeriesPorNome(any(), any())).thenReturn(instanciaPageSerie());
        String msgEsperada = "Já existe Serie com o nome matrix cadastrado";

        ItemExistenteException exception = assertThrows(ItemExistenteException.class, () -> serieServiceMock
                .salvarSerie(MidiaDtoBuilder.builder().build()));

        assertEquals(msgEsperada, exception.getMessage());
        verify(serieRepositoryMock, times(1)).buscarSeriesPorNome(any(), any());
    }

    @Test
    void quandoBuscarListaSeriesForChamadoComRetornoDeFindAllValidoDeveRetornarPageValido() {
        Pageable pageable = instanciaPageable();
        when(serieRepositoryMock.findAll(pageable)).thenReturn(instanciaPageSerie());

        Page<MidiaDTO> result = serieServiceMock.buscarListaSeries(pageable);

        assertEquals(result.getTotalPages(), 1);
        verify(serieRepositoryMock, times(1)).findAll(pageable);
    }

    @Test
    void quandoBuscarListaSeriesForChamadoComRetornoDeFindAllVazioDeveRetornarItemNotFoundException() {
        Pageable pageable = instanciaPageable();
        when(serieRepositoryMock.findAll(pageable)).thenReturn(new PageImpl<>(new ArrayList<>()));
        String msgEsperada = "Nenhum(a) Serie cadastrado na base";

        ItemNotFoundException exception = assertThrows(ItemNotFoundException.class, () -> serieServiceMock
                .buscarListaSeries(instanciaPageable()));

        assertEquals(msgEsperada, exception.getMessage());
        verify(serieRepositoryMock, times(1)).findAll(pageable);
    }

    @Test
    void quandoBuscarSeriesPorNomeForChamadoPassandoSerieValidoDeveRetornarPageValido() {
        MidiaDTO midia = MidiaDtoBuilder.builder().build();
        when(serieRepositoryMock.buscarSeriesPorNome(any(), any())).thenReturn(instanciaPageSerie());
        when(midiaMapperMock.converteSerieEntidadeParaDto(any())).thenReturn(midia);

        Page<MidiaDTO> result = serieServiceMock.buscarSeriesPorNome("", instanciaPageable());

        assertEquals(result.getTotalPages(), 1);
        verify(serieRepositoryMock, times(1)).buscarSeriesPorNome(any(), any());
        verify(midiaMapperMock, times(1)).converteSerieEntidadeParaDto(any());
    }

    @Test
    void quandoBuscarSeriesPorNomeForChamadoPassandoSerieInvalidoDeveRetornarItemNotFoundException() {
        when(serieRepositoryMock.buscarSeriesPorNome(any(), any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        String msgEsperada = "Serie madMax não encontrado";

        ItemNotFoundException exception = assertThrows(ItemNotFoundException.class, () -> serieServiceMock
                .buscarSeriesPorNome("madMax", instanciaPageable()));

        assertEquals(msgEsperada, exception.getMessage());
        verify(serieRepositoryMock, times(1)).buscarSeriesPorNome(any(), any());
    }

    @Test
    void quandoEditarSerieForChamadoPassandoMidiaValidoDeveRetornarMidiaDto() {
        MidiaDTO midia = MidiaDtoBuilder.builder().build();
        when(serieRepositoryMock.findById(any())).thenReturn(Optional.of(new Serie()));
        when(serieRepositoryMock.save(any())).thenReturn(null);
        when(midiaMapperMock.converteSerieEntidadeParaDto(any())).thenReturn(midia);

        MidiaDTO result = serieServiceMock.editarSerie(midia);

        assertEquals(result.getId(), midia.getId());
        verify(serieRepositoryMock, times(1)).findById(any());
        verify(serieRepositoryMock, times(1)).save(any());
        verify(midiaMapperMock, times(1)).converteSerieEntidadeParaDto(any());
    }

    @Test
    void quandoEditarSerieForChamadoPassandoMidiaInvalidaDeveRetornarItemNotFoundException() {
        when(serieRepositoryMock.findById(any())).thenReturn(Optional.ofNullable(null));
        String msgEsperada = "Serie de id 810 não encontrado";

        ItemNotFoundException exception = assertThrows(ItemNotFoundException.class, () -> serieServiceMock
                .editarSerie(MidiaDtoBuilder.builder().build()));

        assertEquals(msgEsperada, exception.getMessage());
        verify(serieRepositoryMock, times(1)).findById(any());
    }

    @Test
    void quandoDeletarSeriePorIdForChamadoPassandoIdValidoDeveDeletarSerie() {
        Serie serie = SerieBuilder.builder().build();
        when(serieRepositoryMock.findById(ID)).thenReturn((Optional.ofNullable(serie)));
        doNothing().when(serieRepositoryMock).deleteById(ID);

        serieServiceMock.deletarSeriePorId(ID);

        verify(serieRepositoryMock, times(1)).findById(ID);
        verify(serieRepositoryMock, times(1)).deleteById(ID);
    }

    @Test
    void quandoDeletarSeriePorIdForChamadoPassandoIdInvalidoDeveRetornarItemNotFoundException() {
        when(serieRepositoryMock.findById(ID)).thenReturn(Optional.ofNullable(null));
        String msgEsperada = "Serie de id 810 não encontrado";

        ItemNotFoundException exception = assertThrows(ItemNotFoundException.class, () -> serieServiceMock
                .deletarSeriePorId(ID));

        assertEquals(msgEsperada, exception.getMessage());
        verify(serieRepositoryMock, times(1)).findById(ID);
    }

    private static Page<Serie> instanciaPageSerie() {
        List<Serie> serieList = new ArrayList<>();
        serieList.add(new Serie());
        Page<Serie> paginaSerie = new PageImpl<>(serieList);
        return paginaSerie;
    }

    private static Pageable instanciaPageable() {
        return PageRequest.of(2, 5);
    }
}
