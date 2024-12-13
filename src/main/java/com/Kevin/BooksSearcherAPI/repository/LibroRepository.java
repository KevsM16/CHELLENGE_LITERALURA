package com.Kevin.BooksSearcherAPI.repository;

import com.Kevin.BooksSearcherAPI.modelos.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LibroRepository extends JpaRepository<Libro,Long>  {

    @Query("SELECT l FROM Libro l JOIN l.autores a WHERE :anoColocado < a.fechaMuerte")
    List<Libro> autoresPorAno(int anoColocado);

    List<Libro> findByIdiomas(String idioma);
}
