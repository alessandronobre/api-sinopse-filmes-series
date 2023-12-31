package com.devpro.sinopsefs.model;

import com.devpro.sinopsefs.enums.Genero;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table
public class Serie extends Midia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_serie")
    private Long id;

    @ElementCollection(targetClass = Genero.class)
    @CollectionTable(name = "genero_serie", joinColumns = @JoinColumn(name = "cod_serie"))
    @Enumerated(EnumType.STRING)
    @Column(name = "genero")
    private List<Genero> genero;

}