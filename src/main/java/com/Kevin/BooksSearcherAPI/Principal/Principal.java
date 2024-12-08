package com.Kevin.BooksSearcherAPI.Principal;


import com.Kevin.BooksSearcherAPI.Service.ConsumoAPI;
import com.Kevin.BooksSearcherAPI.Service.ConsumoDatos;
import com.Kevin.BooksSearcherAPI.modelos.DatosAutor;
import com.Kevin.BooksSearcherAPI.modelos.DatosGenerales;
import com.Kevin.BooksSearcherAPI.modelos.DatosLibro;
import com.Kevin.BooksSearcherAPI.modelos.Libro;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {

    private static final String URL_API="https://gutendex.com/books/";
    List<DatosGenerales> listaLibros= new ArrayList<>();
    List<Libro> datos;
    List<Libro>librosBuscados=new ArrayList<>();
    Scanner lectura= new Scanner(System.in);



    public void correMenu(){
        var opcion=-1;

    String opciones_menu= """
            
            1.Buscar libro por titulo
            2.Lista de los libros que fueron encontrados
            0.Salir
            
            """;
    while(opcion!=0){
        System.out.println("Bienvenido al buscador de libros :D, para empezar seleccione una opcion "+opciones_menu);
        opcion= lectura.nextInt();
        lectura.nextLine();

        switch(opcion){
            case 1:
                buscarLibrosPorTitulo();
                break;
            case 2:
                buscarLibrosBuscados();
                ;
                break;
            case 0:
                System.out.println("Gracias por usar el buscador de libros adios! :D");
                break;

            default:
                System.out.println("Opcion no encontrada ");
                break;
        }
    }
    }




    public DatosGenerales buscarLibro(){
        ConsumoAPI consumoAPI=new ConsumoAPI();
       String json = consumoAPI.leer(URL_API);
        ConsumoDatos consumoDatos=new ConsumoDatos();
       var libros= consumoDatos.Consumo(json,DatosGenerales.class);

        return libros;
    }

    public void buscarLibros(){
        DatosGenerales datosGenerales=buscarLibro();
        listaLibros.add(datosGenerales);
        datos= listaLibros.stream().flatMap(l->l.datosLibros().stream()).map(DatosLibro-> new Libro(DatosLibro)).collect(Collectors.toUnmodifiableList());
        datos.forEach(System.out::println);

    }

    public void buscarLibrosPorTitulo() {
        buscarLibros();
        System.out.println("Coloque el titulo que desea buscar ");
        var titulo = lectura.nextLine();
        Optional<Libro> tituloLibro = datos.stream().filter(t -> t.getTitulo().toLowerCase().contains(titulo.toLowerCase())).findFirst();

      if(tituloLibro.isPresent()){
          System.out.println("El libro buscado es :"+tituloLibro.get());
         librosBuscados.add(tituloLibro.get());
      }else{
          System.out.println("Ningun libro fue encontrado");
      }
            }

    public void buscarLibrosBuscados() {
        librosBuscados.forEach(System.out::println);
    }
}




