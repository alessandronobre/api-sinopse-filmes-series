package com.devpro.sinopsefs.repository;

import com.devpro.sinopsefs.model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {

    @Query(value="SELECT * " +
            "FROM FILME " +
            "WHERE NOME ILIKE %?1% ", nativeQuery = true)
    List<Filme> buscarFilmesPorNome(String nome);
}
