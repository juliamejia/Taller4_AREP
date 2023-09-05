package org.example.spark;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * La clase Spark proporciona métodos para el enrutamiento web y el almacenamiento en caché de respuestas HTTP.
 */
public class Spark {

    // Almacena las respuestas en caché utilizando un mapa con rutas como clave y respuestas como valor.
    public static Map<String, Response> cache = new HashMap<>();

    /**
     * Maneja solicitudes GET y almacena la respuesta en caché.
     *
     * @param path  La ruta de la solicitud.
     * @param route El objeto Route que manejará la solicitud.
     */
    public static void get(String path, Route route) {
        Request req = new Request();
        Response res = new Response();
        String body = route.handle(req, res);
        res.setBody(body);
        res.setPath(path);
        cache.put(path, res);
    }

    /**
     * Lee un archivo desde el sistema de archivos, almacena su contenido en caché y lo devuelve como respuesta.
     *
     * @param path La ruta del archivo a leer y almacenar en caché.
     * @return La respuesta HTTP que contiene el contenido del archivo.
     */
    public static String setCache(String path) {
        Response res = new Response();
        String path2 = "src/main/resources" + path;
        byte[] content = new byte[0];
        try {
            Path file = Paths.get(path2);
            content = Files.readAllBytes(file);
            String type = (path2).split("\\.")[1];
            res.setType("text/" + type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        res.setBody(new String(content));
        cache.put(path, res);
        return res.getResponse();
    }

    /**
     * Crea una respuesta JSON para solicitudes POST y la almacena en caché.
     *
     * @param value El valor para el cuerpo de la respuesta JSON.
     * @param key   La clave para el cuerpo de la respuesta JSON.
     * @return La respuesta HTTP que contiene el cuerpo de la respuesta JSON.
     */
    public static String post(String value, String key) {
        Response res = new Response();
        String body = "{" + key + ":" + value + "}";
        res.setBody(body);
        res.setType("application/json");
        cache.put(key, res);
        return res.getResponse();
    }
}
