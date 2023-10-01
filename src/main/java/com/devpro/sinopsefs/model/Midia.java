package com.devpro.sinopsefs.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@MappedSuperclass
public abstract class Midia {

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String sinopse;

}
