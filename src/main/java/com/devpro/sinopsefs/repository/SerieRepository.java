package com.devpro.sinopsefs.repository;

import com.devpro.sinopsefs.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SerieRepository extends JpaRepository<Serie, Long> {

    @Query(value="SELECT * " +
            "FROM SERIE " +
            "WHERE NOME ILIKE %?1% ", nativeQuery = true)
    List<Serie> buscarSeriesPorNome(String nome);
}
