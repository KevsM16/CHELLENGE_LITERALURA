package com.Kevin.BooksSearcherAPI.modelos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.awt.*;
import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(@JsonAlias("title") String titulo,
                         @JsonAlias("download_count") int descargasTotales,
                         @JsonAlias("languages") List<String> idiomas,
                         @JsonAlias("authors")List<DatosAutor>autores) {
}
