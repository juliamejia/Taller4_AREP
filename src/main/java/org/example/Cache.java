package org.example;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Memoria cache de la API
 */
public class Cache {
    public static ConcurrentHashMap<String,String> peliculas = new ConcurrentHashMap<>();

    // Método para buscar un título en la caché o en la API si no se encuentra
    public static String buscarTitulo(String titulo) throws IOException {
        String valor="";
        // Verifica si el título ya está en la caché
        if (peliculas.containsKey(titulo)){
            valor += peliculas.get(titulo);
        }else{
            // Si el título no está en la caché, realiza una solicitud a la API y almacena en la caché
            valor += HttpConnection.RsponseApi(titulo);
            peliculas.put(titulo,valor);
        }
        return valor;
    }

}