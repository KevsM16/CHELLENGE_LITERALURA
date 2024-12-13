package com.Kevin.BooksSearcherAPI.principal;


import com.Kevin.BooksSearcherAPI.service.ConsumoAPI;
import com.Kevin.BooksSearcherAPI.service.ConsumoDatos;
import com.Kevin.BooksSearcherAPI.repository.LibroRepository;
import com.Kevin.BooksSearcherAPI.modelos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {
@Autowired
private LibroRepository repositorio;


    private static final String URL_API="https://gutendex.com/books/";
    List<DatosGenerales> listaLibros= new ArrayList<>();
    List<Libro> datos;
    List<Libro> autoresVivos;
    Scanner lectura= new Scanner(System.in);
    public Principal(LibroRepository repository) {
    this.repositorio=repository;
    }

    public void correMenu(){
        var opcion=-1;
    String opciones_menu= """
            
            1.Buscar libro por titulo
            2.Lista de los libros que fueron encontrados
            3.Lista de los autores de los libros que fueron registrados 
            4. Filtrar autores vivos por determinado año
            5. Filtrar libros por idioma colocado
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
                mostrarLibrosBuscados();
                break;
            case 3:
           mostrarAutoresRegistrados();
                break;
            case 4:
                buscarAutoresVivosPorAnoColocado();
                break;
            case 5:
                mostrarLibrosPorIdioma();
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
      if(tituloLibro.isPresent()) {
          var autor_con_libro = tituloLibro.get();
          List<Autor> autor = autor_con_libro.getAutores();
          autor_con_libro.setAutores(autor);
          try {
              repositorio.save(autor_con_libro);
              System.out.println("El libro buscado es :"+'\n'+autor_con_libro+'\n'+"------------");
          } catch (DataIntegrityViolationException e) {
              System.out.println("Ya este libro existe en la base de datos");
          }
      }else{
          System.out.println("Ningun libro fue encontrado");
      }
        System.out.println("Presiona enter para continuar");
      lectura.nextLine();
    }

    public void mostrarLibrosBuscados() {
    var libros= repositorio.findAll();
    libros.stream().forEach(System.out::println);
        System.out.println("Presiona enter para continuar");
        lectura.nextLine();
    }

   public void mostrarAutoresRegistrados() {
   var autores=repositorio.findAll();
       System.out.println("Los autores registrados son: "+autores.stream().flatMap(a->a.getAutores().stream()).collect(Collectors.toList()));

       System.out.println("Presione enter para continuar");
       lectura.nextLine();
    }

    public void mostrarLibrosPorIdioma(){
        String listaIdiomas=
                """
                es-español
                en-ingles
                fr-frances
                pt-portugues
                """;
        System.out.println("Seleccione el idioma para mostrar los libros disponibles en ese idioma"+listaIdiomas);
        var idioma=lectura.nextLine();
        var libroPorIdiomas= repositorio.findByIdiomas(idioma);
        System.out.println("Los libros que estan en el idioma que colocaste son:"+'\n'+libroPorIdiomas.stream().collect(Collectors.toList()));
        System.out.println("presiona enter para continuar");
        lectura.nextLine();
    }

    public void buscarAutoresVivosPorAnoColocado(){

        try{
            System.out.println("Coloque el año en donde desea ver los autores vivos ");
            var ano= lectura.nextInt();
            lectura.nextLine();
            autoresVivos=repositorio.autoresPorAno(ano);
        }catch(InputMismatchException e){
            System.out.println("Recuerda que solo debes colocar numeros");
        }
        System.out.println("Los autores vivos en determinado año son: "+autoresVivos.stream().flatMap(a->a.getAutores().stream()).collect(Collectors.toUnmodifiableList()));
        System.out.println("presiona enter para continuar");
        lectura.nextLine();
    }

}





