package com.Kevin.BooksSearcherAPI.Principal;

import com.Kevin.BooksSearcherAPI.Service.ConsumoAPI;
import com.Kevin.BooksSearcherAPI.modelos.Libro;

import java.util.Scanner;

public class Principal {
private static final String URL_API="gutendex.com/books";
    public void correMenu(){

    var opcion=-1;
    Scanner lectura= new Scanner(System.in);

    String opciones_menu= """
            
            1.Buscar todos los libros
            0.Salir
            """;
    while(opcion!=0){
        System.out.println("Bienvenido al busador de libros :D, para empezar seleccione una opcion "+opciones_menu);
        opcion= lectura.nextInt();

        switch(opcion){
            case 1:
                buscarLibro();
                break;
            case 0:
                System.out.println("Gracias por usar el buscador de libros adios! :D");
                break;
        }
    }
    }
    public Libro buscarLibro(){
        ConsumoAPI consumoAPI=new ConsumoAPI();
        consumoAPI.leer()

    }
}
