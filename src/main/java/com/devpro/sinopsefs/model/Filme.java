package com.devpro.sinopsefs.model;

import com.devpro.sinopsefs.dto.MidiaDTO;
import com.devpro.sinopsefs.enums.Genero;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table
public class Filme extends Midia{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cod_filme")
    private Long id;

    @ElementCollection(targetClass = Genero.class)
    @CollectionTable(name = "genero_filme", joinColumns = @JoinColumn(name = "cod_filme"))
    @Enumerated(EnumType.STRING)
    @Column(name = "genero")
    private List<Genero> genero;

    public Filme(MidiaDTO midia) {
        super(midia.getNome(), midia.getSinopse());
        this.id = midia.getId();
        this.genero = midia.getGeneros();
    }
}
