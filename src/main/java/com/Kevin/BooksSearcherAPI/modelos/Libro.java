package com.Kevin.BooksSearcherAPI.modelos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Libro {
  private String titulo;
  private int descargasTotales;
  private List<String> idiomas;
   private List<DatosAutor>autores;
    private List<DatosGenerales>datos;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getDescargasTotales() {
        return descargasTotales;
    }

    public void setDescargasTotales(int descargasTotales) {
        this.descargasTotales = descargasTotales;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public List<DatosAutor> getAutores() {
        return autores;
    }

    public void setAutores(List<DatosAutor> autores) {
        this.autores = autores;

        }

        public Libro(){}
  public Libro(DatosLibro l){
        this.titulo= l.titulo();
        this.descargasTotales=l.descargasTotales();
        this.idiomas=l.idiomas();
        this.autores=l.autores();
  }

    public String toString() {
        return "-LIBRO-\uD83D\uDCD5"+'\n'+
                "Titulo='" + titulo + '\n'+
                "DescargasTotales=" + descargasTotales +'\n'+
                "Idiomas=" + idiomas +'\n'+
                "Autores=" + autores +'\n';
    }
}
