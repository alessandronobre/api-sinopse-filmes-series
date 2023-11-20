package com.devpro.sinopsefs.controller;

import com.devpro.sinopsefs.dto.MidiaDTO;
import com.devpro.sinopsefs.hateoas.FilmeAssembler;
import com.devpro.sinopsefs.service.FilmeService;
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
public class FilmeControllerTest {

    @InjectMocks
    private FilmeController filmeControllerMock;

    @Mock
    private FilmeService filmeServiceMock;

    @Mock
    private FilmeAssembler filmeAssemblerMock;

    @Mock
    private PagedResourcesAssembler<MidiaDTO> pagedResourcesAssembler;

    @Test
    void quandoCadastarFilmeForChamadoPassandoDadosValidosDeveRetornarItemCadastrado() {
        EntityModel<MidiaDTO> resource = EntityModel.of(MidiaDtoBuilder.builder().build());
        when(filmeAssemblerMock.toModel(any())).thenReturn(resource);

        EntityModel<MidiaDTO> result = filmeControllerMock.cadastarFilme(MidiaDtoBuilder.builder().build());

        assertEquals(resource.getContent().getId(), result.getContent().getId());
        verify(filmeAssemblerMock, times(1)).toModel(any());
    }

    @Test
    void quandoBuscarListaFilmesForChamadoDeveRetornarListaDeFilmes() {
        Pageable pageable = instanciaPageable();
        when(filmeServiceMock.buscarListaFilmes(pageable)).thenReturn(instanciaPageMidiaDto());

        CollectionModel<EntityModel<MidiaDTO>> result = filmeControllerMock.buscarListaFilmes(pageable);

        assertNull(result);
        verify(filmeServiceMock, times(1)).buscarListaFilmes(pageable);
    }

    @Test
    void quandoBuscarFilmesPorNomeForChamadoDeveRetornarListaDeFilmes() {
        Pageable pageable = instanciaPageable();
        when(filmeServiceMock.buscarFilmesPorNome("matrix", pageable)).thenReturn(instanciaPageMidiaDto());

        CollectionModel<EntityModel<MidiaDTO>> result = filmeControllerMock
                .buscarFilmesPorNome(MidiaDtoBuilder.builder().build().getNome(), pageable);

        assertNull(result);
        verify(filmeServiceMock, times(1)).buscarFilmesPorNome("matrix", pageable);
    }

    @Test
    void quandoEditarFilmeForChamadoPassandoDadosValidosDeveRetornarItemCadastrado() {
        EntityModel<MidiaDTO> resource = EntityModel.of(MidiaDtoBuilder.builder().build());
        when(filmeAssemblerMock.toModel(any())).thenReturn(resource);

        EntityModel<MidiaDTO> result = filmeControllerMock.editarFilme(MidiaDtoBuilder.builder().build());

        assertEquals(resource.getContent().getId(), result.getContent().getId());
        verify(filmeAssemblerMock, times(1)).toModel(any());
    }

    @Test
    void quandoDeletarFilmePorIdForChamadoDeveRetornarNoContent() {
        doNothing().when(filmeServiceMock).deletarFilmePorId(any());

        ResponseEntity result = filmeControllerMock.deletarFilmePorId(1L);

        assertEquals(ResponseEntity.noContent().build().getStatusCode(), result.getStatusCode());
        verify(filmeServiceMock, times(1)).deletarFilmePorId(any());
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
