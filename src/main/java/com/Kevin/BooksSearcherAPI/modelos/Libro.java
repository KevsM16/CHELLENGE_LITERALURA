package com.Kevin.BooksSearcherAPI.modelos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)

@Entity
@Table(name="Libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

  private int descargasTotales;


  private String idiomas;

  @OneToMany(mappedBy = "libros", cascade= CascadeType.ALL,fetch = FetchType.EAGER)
   private List<Autor>autores=new ArrayList<>();



  public List <String> getNombreAutor(){
      return autores.stream().map(a->a.getNombreAutor()).collect(Collectors.toList());
  }
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

    public  String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }


    public void setAutores(List<Autor> autores) {
           autores.forEach(autor -> autor.setLibros(this));
           this.autores=autores;
        }

    public List<Autor> getAutores() {
        return autores;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Libro(){}
  public Libro(DatosLibro l){
        this.titulo= l.titulo();
        this.descargasTotales=l.descargasTotales();
        this.idiomas=l.idiomas()!=null && l.idiomas().isEmpty()? null:l.idiomas().get(0);
        this.autores=l.autores().stream().map(a->new Autor(a)).collect(Collectors.toList());

  }

    public String toString() {
        return "-LIBRO-\uD83D\uDCD5"+'\n'+
                "Titulo='" + titulo + '\n'+
                "Descargas totales del libro=" + descargasTotales +'\n'+
                "Idiomas del libro =" + idiomas +'\n'+
                "Autor=" +getNombreAutor().get(0)+'\n';
    }
}

