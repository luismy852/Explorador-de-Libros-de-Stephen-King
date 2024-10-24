package com.bruno.stephenkingapi.principal;

import com.bruno.stephenkingapi.models.*;
import com.bruno.stephenkingapi.service.ConsumoApi;
import com.bruno.stephenkingapi.service.ConvierteDatos;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    ConsumoApi consulta = new ConsumoApi();
    ConvierteDatos convierteDatos = new ConvierteDatos();
    Scanner teclado = new Scanner(System.in);
    public void mostrarMenu (){
        //Consulta libros
        var json = consulta.obtenerDatos("https://stephen-king-api.onrender.com/api/books");
        var datos = convierteDatos.obtenerDatos(json, Datos.class);
        //consultas libros cortos
        var jsonC = consulta.obtenerDatos("https://stephen-king-api.onrender.com/api/shorts");
        var datosC = convierteDatos.obtenerDatos(jsonC, DatosShort.class);
        //consulta villanos
        var jsonV = consulta.obtenerDatos("https://stephen-king-api.onrender.com/api/villains");
        var datosV = convierteDatos.obtenerDatos(jsonV, DatosV.class);

        Integer decision = 0;
        List<DatosLibros> librosConsultados = new ArrayList<>();
        List<DatosCuentos> cuentosConsultados = new ArrayList<>();
        List<DatosVillano> villanosConsultados = new ArrayList<>();

        while (decision != 5) {
            System.out.println("""
        ******************************************
        Bienvenido, aqui encontraras todos los libros de Stephen King
        
        Escribe el numero de la opción que desees:
        1. Buscar libro por título
        2. Buscar cuentos por título
        3. Buscar villanos
        4. Estadísticas
        5. Salir
        
        """);

            try {
                decision = teclado.nextInt();
            } catch (InputMismatchException e){
                decision =10;
            }
            teclado.nextLine();

            switch (decision){
                case 1:
                    int decisionBusqueda = 0;
                    while (decisionBusqueda !=2){
                        System.out.println("Busca el título de los libros en ingles");
                        var busquedaUsuario = teclado.nextLine();
                        Optional<DatosLibros> datosLibros = datos.resultados().stream()
                                .filter(l -> l.titulo().toUpperCase().contains(busquedaUsuario.toUpperCase()))
                                .findFirst();

                        if (datosLibros.isPresent()){
                            System.out.println("Libro encontrado");
                            System.out.println(datosLibros.get());
                        } else {
                            System.out.println("Libro no encontrado");
                            break;
                        }

                        System.out.println("""
                            1. Agregar libro consultado a la lista
                            2. Volver al menú
                            """);
                        decisionBusqueda = teclado.nextInt();
                        if (decisionBusqueda == 1){
                            librosConsultados.add(datosLibros.get());
                            System.out.println("Libro agregado con éxito");
                            break;
                        } else if (decisionBusqueda ==2) {
                            break;
                        }
                    }
                        break;
                case 2:
                    decisionBusqueda = 0;
                    while (decisionBusqueda !=2){
                        System.out.println("Busca el título de los libros en ingles");
                        var busquedaUsuario = teclado.nextLine();
                        Optional<DatosCuentos> datosLibrosCortos = datosC.resultados().stream()
                                .filter(l -> l.titulo().toUpperCase().contains(busquedaUsuario.toUpperCase()))
                                .findFirst();

                        if (datosLibrosCortos.isPresent()){
                            System.out.println("Libro encontrado");
                            System.out.println(datosLibrosCortos.get());
                        } else {
                            System.out.println("Libro no encontrado");
                            break;
                        }

                        System.out.println("""
                            1. Agregar libro consultado a la lista
                            2. Volver al menú
                            """);
                        decisionBusqueda = teclado.nextInt();
                        if (decisionBusqueda == 1){
                            cuentosConsultados.add(datosLibrosCortos.get());
                            System.out.println("Libro agregado con éxito");
                            break;
                        } else if (decisionBusqueda ==2) {
                            break;
                        }
                    }
                    break;

                case 3:
                    decisionBusqueda = 0;
                    while (decisionBusqueda !=2){
                        System.out.println("Busca por el nombre del villano");
                        var busquedaUsuario = teclado.nextLine();
                        Optional<DatosVillano> datosVillanos = datosV.resultados().stream()
                                .filter(v -> v.nombre().toUpperCase().contains(busquedaUsuario.toUpperCase()))
                                .findFirst();

                        if (datosVillanos.isPresent()){
                            System.out.println("Villano encontrado");
                            System.out.println(datosVillanos.get());
                        } else {
                            System.out.println("Villano no encontrado");
                            break;
                        }

                        System.out.println("""
                            1. Agregar libro consultado a la lista
                            2. Volver al menu
                            """);
                        decisionBusqueda = teclado.nextInt();
                        if (decisionBusqueda == 1){
                            villanosConsultados.add(datosVillanos.get());
                            System.out.println("Libro agregado con éxito");
                            break;
                        } else if (decisionBusqueda==2) {
                            break;
                        }
                    }
                    break;

                case 4:
                    System.out.println("Bienvenido estas son las estadisticas de los libros en base a sus paginas");
                    IntSummaryStatistics iss = datos.resultados().stream()
                            .collect(Collectors.summarizingInt(DatosLibros::paginas));

                    System.out.println("El libro con mas paginas tiene " + iss.getMax() + " paginas");
                    System.out.println("El libro con menos paginas tiene " + iss.getMin() + " paginas");
                    System.out.println("Todas las paginas de todos los libros suman " + iss.getSum() + " paginas");
                    System.out.println("Estas estadísticas se realizaron con " + iss.getCount() + " Libros");
                    break;

                case 5:
                    System.out.println("""
                            Estos fueron los libros que consultaste:
                            """);
                    librosConsultados.stream()
                            .map(l-> l.titulo())
                            .forEach(System.out::println);
                    System.out.println("Estos fueron los libros cortos que consultaste:");
                    cuentosConsultados.stream()
                            .map(c-> c.titulo())
                            .forEach(System.out::println);
                    System.out.println("Estos fueron los villanos que consultaste:");
                    villanosConsultados.stream()
                            .map(v-> v.nombre())
                            .forEach(System.out::println);
                    System.out.println("""
                            Gracias por usar el programa
                            """);

                default:
                    System.out.println("Pon un valor correcto");

            }
        }
    }
}
