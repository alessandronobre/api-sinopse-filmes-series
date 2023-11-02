package com.devpro.sinopsefs.mapper.midia.mapper;

import com.devpro.sinopsefs.dto.MidiaDTO;
import com.devpro.sinopsefs.mapper.MidiaMapper;
import com.devpro.sinopsefs.mapper.midia.builders.FilmeBuilder;
import com.devpro.sinopsefs.mapper.midia.builders.MidiaDtoBuilder;
import com.devpro.sinopsefs.mapper.midia.builders.SerieBuilder;
import com.devpro.sinopsefs.model.Filme;
import com.devpro.sinopsefs.model.Serie;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class MidiaMapperTest {

    @InjectMocks
    private MidiaMapper midiaMapperMock;

    private static final Long ID = 810L;

    @Test
    void quandoConverteFilmeEntidadeParaMidiaDtoForChamadoPassandoFilmeDeveRetornarMidiaDTO() {
        MidiaDTO result = midiaMapperMock.converteFilmeEntidadeParaMidiaDto(FilmeBuilder.builder().build());

        assertEquals(result.getId(), ID);
    }

    @Test
    void quandoConverteSerieEntidadeParaMidiaDtoForChamadoPassandoSerieDeveRetornarMidiaDTO() {
        MidiaDTO result = midiaMapperMock.converteSerieEntidadeParaMidiaDto(SerieBuilder.builder().build());

        assertEquals(result.getId(), ID);
    }

    @Test
    void quandoConverteMidiaDtoParaFilmeEntidadeForChamadoPassandoMidiaDtoDeveRetornarFilme() {
        Filme result = midiaMapperMock.converteMidiaDtoParaFilmeEntidade(MidiaDtoBuilder.builder().build());

        assertEquals(result.getId(), ID);
    }

    @Test
    void quandoConverteMidiaDtoParaSerieEntidadeForChamadoPassandoMidiaDtoDeveRetornarSerie() {
        Serie result = midiaMapperMock.converteMidiaDtoParaSerieEntidade(MidiaDtoBuilder.builder().build());

        assertEquals(result.getId(), ID);
    }
}
