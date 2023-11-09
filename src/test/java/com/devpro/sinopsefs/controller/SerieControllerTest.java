package com.devpro.sinopsefs.controller;

import com.devpro.sinopsefs.dto.MidiaDTO;
import com.devpro.sinopsefs.hateoas.SerieAssembler;
import com.devpro.sinopsefs.service.SerieService;
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
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SerieControllerTest {

    @InjectMocks
    private SerieController serieControllerMock;

    @Mock
    private SerieService serieServiceMock;

    @Mock
    private SerieAssembler serieAssemblerMock;

    @Mock
    private PagedResourcesAssembler<MidiaDTO> pagedResourcesAssembler;

    @Test
    void quandoCadastarSerieForChamadoPassandoDadosValidosDeveRetornarItemCadastrado() {
        EntityModel<MidiaDTO> resource = EntityModel.of(MidiaDtoBuilder.builder().build());
        when(serieAssemblerMock.toModel(any())).thenReturn(resource);

        EntityModel<MidiaDTO> result = serieControllerMock.cadastarSerie(MidiaDtoBuilder.builder().build());

        assertEquals(resource.getContent().getId(), result.getContent().getId());
        verify(serieAssemblerMock, times(1)).toModel(any());
    }

    @Test
    void quandoBuscarListaSeriesForChamadoDeveRetornarListaDeSeries() {
        Pageable pageable = instanciaPageable();
        when(serieServiceMock.buscarListaSeries(pageable)).thenReturn(instanciaPageMidiaDto());

        CollectionModel<EntityModel<MidiaDTO>> result = serieControllerMock.buscarListaSeries(pageable);

        assertNull(result);
        verify(serieServiceMock, times(1)).buscarListaSeries(pageable);
    }

    @Test
    void quandoBuscarSeriesPorNomeForChamadoDeveRetornarListaDeSeries() {
        Pageable pageable = instanciaPageable();
        when(serieServiceMock.buscarSeriesPorNome("matrix", pageable)).thenReturn(instanciaPageMidiaDto());

        CollectionModel<EntityModel<MidiaDTO>> result = serieControllerMock
                .buscarSeriePorNome(MidiaDtoBuilder.builder().build().getNome(), pageable);

        assertNull(result);
        verify(serieServiceMock, times(1)).buscarSeriesPorNome("matrix", pageable);
    }

    @Test
    void quandoEditarSerieForChamadoPassandoDadosValidosDeveRetornarItemCadastrado() {
        EntityModel<MidiaDTO> resource = EntityModel.of(MidiaDtoBuilder.builder().build());
        when(serieAssemblerMock.toModel(any())).thenReturn(resource);

        EntityModel<MidiaDTO> result = serieControllerMock.editarSerie(MidiaDtoBuilder.builder().build());

        assertEquals(resource.getContent().getId(), result.getContent().getId());
        verify(serieAssemblerMock, times(1)).toModel(any());
    }

    @Test
    void quandoDeletarSeriePorIdForChamadoDeveRetornarItemCadastrado() {
        doNothing().when(serieServiceMock).deletarSeriePorId(any());

        ResponseEntity result = serieControllerMock.deletarSeriePorId(1L);

        assertEquals(ResponseEntity.noContent().build().getStatusCode(), result.getStatusCode());
        verify(serieServiceMock, times(1)).deletarSeriePorId(any());
    }

    private static Page<MidiaDTO> instanciaPageMidiaDto() {
        List<MidiaDTO> midiaList = new ArrayList<>();
        midiaList.add(MidiaDtoBuilder.builder().build());

        Page<MidiaDTO> midias  = new PageImpl<>(midiaList, instanciaPageable(), midiaList.size());
        return midias;
    }

    private static Pageable instanciaPageable() {
        return PageRequest.of(1, 5);
    }
}
