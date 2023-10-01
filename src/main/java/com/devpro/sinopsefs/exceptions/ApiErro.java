package com.devpro.sinopsefs.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiErro {

    private int erro;
    private String status;
    private String mensagem;
    private LocalDateTime timeStamp;
}
