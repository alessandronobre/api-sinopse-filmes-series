package com.devpro.sinopsefs.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CampoErro {

    private String campo;
    private String mensagem;
}
