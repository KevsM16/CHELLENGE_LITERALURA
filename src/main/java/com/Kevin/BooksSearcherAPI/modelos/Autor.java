package com.Kevin.BooksSearcherAPI.modelos;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

@Entity
@Table(name= "Autores")
public class Autor {
   private  String nombreAutor;
   private  int fechaNacimiento;
    private int fechaMuerte;

    @ManyToOne
    private Libro libros;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    public int getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(int fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getFechaMuerte() {
        return fechaMuerte;
    }

    public void setFechaMuerte(int fechaMuerte) {
        this.fechaMuerte = fechaMuerte;
    }

    public Libro getLibros() {
        return libros;
    }

    public void setLibros(Libro libros) {
        this.libros = libros;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
  public Autor(){}

    public Autor(DatosAutor a) {
       this.nombreAutor=a.nombreAutor();
       this.fechaNacimiento=a.fechaNacimiento();
       this.fechaMuerte=a.fechaMuerte();
    }


    @Override
    public String toString() {
        return '\n'+"-----Autor-----" +'\n'+
                 "Nombre del Autor='" + nombreAutor + '\n' +
                 "Fecha de Nacimiento del autor=" + fechaNacimiento +'\n'
               + "Fecha de Muerte del autor=" + fechaMuerte+'\n'
                +"Libro de este autor="+libros.getTitulo();
    }
}
