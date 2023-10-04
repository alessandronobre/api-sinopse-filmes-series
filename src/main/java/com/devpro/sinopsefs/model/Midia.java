package com.devpro.sinopsefs.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@MappedSuperclass
public abstract class Midia {

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false)
    private String sinopse;

}
