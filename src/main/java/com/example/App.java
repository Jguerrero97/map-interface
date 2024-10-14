package com.example;
//docs.oracle.com/javase/tutorial/collections/interfaces/map.html 

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class App 
{
    public static void main( String[] args )
    {
     /*¿Que es un mapa?
 * 
 * Es un tipo de coleccion que almacena la informacion en parejas de clave (key) y valor (vlue),
 * donde las claves no se pueden repetir, es un tipo de hash.
 * 
 * No hereda de la interface Collection, por lo cual se dice que no es una verdadera coleccion, aunque 
 * se puede tratar como una coleccion utilizando las Collection Views (Vistas de Colleccion)
 */

//¿Como se puede crear un mapa? Para ejemplificar, determinar la frecuencia de ocurrencia de 
// una palara en un array o lista de palabras

// Determinar la frecuencia de ocurrencia de una palabra en un array 
// o lista de palabras
List<String> palabras = Arrays.asList("Antonio", "Antonio", "Juan", "Antonio", "Ruben", "Marcos", "Ruben");

Map<String, Long> m = new HashMap<>();

// Para realizar el mapa m, primero utilizaremos una sentencia for mejorada
for (String palabra : palabras) 

    {

    Long frecuenciaOcurrencia = m.get(palabra);
    
    m.put(palabra, frecuenciaOcurrencia == null ? 1L : ++ frecuenciaOcurrencia);
    }
    
    System.out.println(m);
    
    /* Lo anterior esta bien pero es antiguo, actualmente se puede obtener el mismo resultado
    * con OPERACIONES DE AGREGADO (Metodos de la clase Stream, tuberias, lambdas, metodos
    * por referencia, en fin, PROGRAMACION FUNCIONAL)
    */
    
    // palabra -> palabra es similar a utilizar Function.identity()
    
    Map<String, Long> m2 = palabras.stream()
    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    
   System.out.println(m2);

   /*Ejemplos tipicos de creacion de mapas, colecciones Map Interface */

   /* Ejemplo #1
    * 
    * Recorrer la coleccion de personas y obtener un mapa que agrupe las personas por Genero
    * 
    */

    List<Persona> personas = Persona.getPersonas();

    Map<Genero,List<Persona>> personasPorGenero = personas.stream()
        .collect(Collectors.groupingBy(persona -> persona.getGenero()));

        System.out.println("Coleccion de personas agrupadas por Genero");
        System.out.println(personasPorGenero);

        /*Ejemplo #2
         * 
         * Recorrer la lista de personas y obtener una nueva coleccion que agrupe nombres de persona,
         * separados por punto y coma, por genero.
         *  
         */

         Map<Genero, String> nombresPorGenero = personas.stream()
            .collect(Collectors.groupingBy(Persona::getGenero,
            Collectors.mapping(Persona::getNombre, Collectors.joining(";"))));

            System.out.println(nombresPorGenero);
         

    }
}
