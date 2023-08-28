package org.example;


import org.example.Services.RestServiceInterface;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.net.*;
import java.io.*;
import java.util.*;


/**
 * Clase principal que contiene el metodo main que inicia el servidor
 */
public class HttpServer {
    private static HttpServer instance = new HttpServer();
    private Map<String,RestServiceInterface> services = new HashMap<>();
    private HttpServer(){}

    public static HttpServer getInstance(){
        return instance;
    }
    public void main(String[] args) throws IOException {
        // Crea un socket de servidor en el puerto 35000
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }

        boolean running = true;
        while(running) {
            Socket clientSocket = null;
            try {
                // Espera y acepta una conexión entrante del cliente
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
            // Establece flujos de entrada y salida para comunicarse con el cliente
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine, outputLine;
            String uriString = "";
            boolean firs_line = true;
            String request = "/simple";
            // Lee las líneas de entrada de la solicitud HTTP
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received: " + inputLine);
                if(firs_line){
                    request = inputLine.split(" ")[1];
                    firs_line = false;
                }
                if(inputLine.contains("title?name")){
                    String[] firstSplit = inputLine.split("=");
                    uriString = (firstSplit[1].split("HTTP"))[0];
                }
                if (!in.ready()) {
                    break;
                }
            }
            if(request.startsWith("/apps/")) {
                outputLine = executeService(request.substring(5));
            }
            // Decide qué respuesta enviar al cliente
            else if(!Objects.equals(uriString, "")){
                outputLine = getHello(uriString);
            }else {
                outputLine = getIndexResponse();
            }
            out.println(outputLine);
            out.close();
            in.close();
            clientSocket.close();
        }
        serverSocket.close();
    }

    // Define una respuesta JSON con una tabla HTML
    public static String getHello(String uri) throws IOException {
        String response = "HTTP/1.1 200 OK\r\n"
                + "Content-Type: application/json\r\n"
                + "\r\n" +
                "<style>\n" +
                "table, th, td {\n" +
                "  border:1px solid black;\n" +
                "}\n" +
                "</style>"+
                crearTabla(Cache.buscarTitulo(uri));
        return response;
    }

    // Define una respuesta HTML con un formulario y JavaScript
    public static String getIndexResponse(){
        String response = "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n"
                + "<!DOCTYPE html>\n" +
                "<html>\n" +
                "    <head>\n" +
                "        <title>Form Example</title>\n" +
                "        <meta charset=\"UTF-8\">\n" +
                "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    </head>\n" +
                "    <body>\n" +
                "        <h1>Search your movie GET</h1>\n" +
                "        <form action=\"/hello\">\n" +
                "            <label for=\"name\">Title:</label><br>\n" +
                "            <input type=\"text\" id=\"name\" name=\"name\" value=\"John\"><br><br>\n" +
                "            <input type=\"button\" value=\"Submit\" onclick=\"loadGetMsg()\">\n" +
                "        </form> \n" + "<br>"+
                "        <div id=\"getrespmsg\"></div>\n" +
                "\n" +
                "        <script>\n" +
                "            function loadGetMsg() {\n" +
                "                let nameVar = document.getElementById(\"name\").value;\n" +
                "                const xhttp = new XMLHttpRequest();\n" +
                "                xhttp.onload = function() {\n" +
                "                    document.getElementById(\"getrespmsg\").innerHTML =\n" +
                "                    this.responseText;\n" +
                "                }\n" +
                "                xhttp.open(\"GET\", \"/title?name=\"+nameVar);\n" +
                "                xhttp.send();\n" +
                "            }\n" +
                "        </script>\n" +
                "\n" +
                "</html>";
        return response;

    }



    // Crea una tabla HTML a partir de datos proporcionados
    private static String crearTabla(String respuestaApi){
        String[] datos = respuestaApi.split(":");
        String tabla = "<table> \n";
        for (int i = 0;i<(datos.length);i++) {
            String[] respuestaTemporal = datos[i].split(",");
            tabla+="<td>" + Arrays.toString(Arrays.copyOf(respuestaTemporal, respuestaTemporal.length - 1)).replace("[","").replace("]","").replace("}","") + "</td>\n</tr>\n";
            tabla+="<tr>\n<td>" +  respuestaTemporal[respuestaTemporal.length-1].replace("{","").replace("[","") + "</td>\n";
        }
        tabla += "</table>";
        return tabla;

    }

    private String executeService(String serviceName){
        RestServiceInterface rs = services.get(serviceName);
        String header = rs.getHeader();
        String body = rs.getResponse();
        return header + body;
    }

    public void addServices(String key, RestServiceInterface service){
        services.put(key,service);
    }


}
