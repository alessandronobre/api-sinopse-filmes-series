package com.devpro.sinopsefs.exceptions;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ValidacaoApiErro extends ApiErro {

    private List<CampoErro> erros;

    public ValidacaoApiErro(int erro, String status, LocalDateTime timestamp, String mensagem, List<CampoErro> erros) {
        super(erro, status, mensagem, timestamp);
        this.erros = erros;
    }
}
