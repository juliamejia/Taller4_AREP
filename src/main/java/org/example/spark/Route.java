/**
 * La interfaz Route define un contrato para manejar solicitudes HTTP en una aplicación web.
 */
package org.example.spark;

public interface Route {

    /**
     * Maneja una solicitud HTTP y devuelve una respuesta.
     *
     * @param req La solicitud HTTP entrante representada por un objeto Request.
     * @param res La respuesta HTTP que se debe generar y enviar como un objeto Response.
     * @return Una cadena que representa la respuesta HTTP, que se puede enviar al cliente.
     */
    String handle(Request req, Response res);
}
