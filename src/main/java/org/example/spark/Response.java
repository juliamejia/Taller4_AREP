/**
 * La clase Response representa una respuesta HTTP simple que se puede utilizar en una aplicación web.
 */
package org.example.spark;

public class Response {

    // Atributos privados que almacenan información sobre la respuesta HTTP.
    private String type;
    private String path;
    private String body;

    /**
     * Obtiene la cabecera HTTP de la respuesta.
     *
     * @return La cadena de cabecera HTTP que incluye el código de estado y el tipo de contenido.
     */
    public String getHeader() {
        return "HTTP/1.1 200 OK\r\n" +
                "Content-type: " + type + "\r\n" +
                "\r\n";
    }

    /**
     * Obtiene la respuesta HTTP completa, que incluye la cabecera y el cuerpo.
     *
     * @return La respuesta HTTP completa como una cadena.
     */
    public String getResponse() {
        return getHeader() + body;
    }

    /**
     * Establece el cuerpo de la respuesta HTTP.
     *
     * @param body El cuerpo de la respuesta HTTP como una cadena.
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * Obtiene el tipo de contenido de la respuesta.
     *
     * @return El tipo de contenido como una cadena.
     */
    public String getType() {
        return type;
    }

    /**
     * Obtiene la ruta de la respuesta.
     *
     * @return La ruta de la respuesta como una cadena.
     */
    public String getPath() {
        return path;
    }

    /**
     * Establece el tipo de contenido de la respuesta.
     *
     * @param type El tipo de contenido como una cadena.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Establece la ruta de la respuesta.
     *
     * @param path La ruta de la respuesta como una cadena.
     */
    public void setPath(String path) {
        this.path = path;
    }
}
