package com.Kevin.BooksSearcherAPI.modelos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;

@JsonIgnoreProperties(ignoreUnknown = true)

public record DatosAutor(
        @JsonAlias("name")
        String nombreAutor,
        @JsonAlias("birth_year")
        int fechaNacimiento,
        @JsonAlias("death_year")
        int fechaMuerte) {
}
