package org.example;

import org.example.controller.annotations.Component;
import org.example.controller.annotations.RequestMapping;
import org.example.spark.Response;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.*;
import java.io.*;
import java.util.*;

/**
 * Clase principal que contiene el método main que inicia el servidor HTTP.
 */
public class HttpServer {
    private static HttpServer instance = new HttpServer();
    private Map<String, Method> services = new HashMap<String, Method>();
    private Response res;
    private static OutputStream outputStream = null;
    private final String root = "org/example/controller";

    private HttpServer() {
    }

    /**
     * Obtiene una instancia única de la clase HttpServer.
     *
     * @return Una instancia única de HttpServer.
     */
    public static HttpServer getInstance() {
        return instance;
    }

    /**
     * El método principal que inicia el servidor HTTP.
     *
     * @param args Argumentos de línea de comandos (no se utilizan en este caso).
     * @throws IOException Si ocurre un error de E/S al iniciar el servidor.
     */
    public void run(String[] args) throws IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        List<Class<?>> classes = getClasses();
        for (Class<?> clasS:classes){
            if(clasS.isAnnotationPresent(Component.class)){
                Class<?> c = Class.forName(clasS.getName());
                Method[] m = c.getMethods();
                for (Method me: m){
                    if(me.isAnnotationPresent(RequestMapping.class)){
                        String key = me.getAnnotation(RequestMapping.class).value();
                        services.put(key,me);
                    }
                }
            }

        }
        // Crea un socket de servidor en el puerto 35000
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }

        boolean running = true;
        while (running) {
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
            String inputLine, outputLine = null;
            String uriString = "";
            boolean firstLine = true;
            String request = "/simple";
            String verb = "";
            outputStream = clientSocket.getOutputStream();
            // Lee las líneas de entrada de la solicitud HTTP
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received: " + inputLine);
                if (firstLine) {
                    String[] requestTokens = inputLine.split(" ");
                    if (requestTokens.length >= 2) {
                        request = requestTokens[1];
                        verb = requestTokens[0];
                    }
                    firstLine = false;
                }
                if (inputLine.contains("title?name")) {
                    String[] firstSplit = inputLine.split("=");
                    uriString = firstSplit[1].split("HTTP")[0];
                }
                if (!in.ready()) {
                    break;
                }
            }
            if (Objects.equals(verb, "GET")) {
                System.out.println(request);
                if(services.containsKey(request)){
                    outputLine = services.get(request).invoke(null).toString();
                }
            }
            // Decide qué respuesta enviar al cliente
            else if (!Objects.equals(uriString, "")) {
                outputLine = getHello(uriString);
            } else {
                outputLine = getIndexResponse();
            }
            out.println(outputLine);
            out.close();
            in.close();
            clientSocket.close();
        }
        serverSocket.close();
    }

    /**
     * Define una respuesta JSON con una tabla HTML.
     *
     * @param uri La URI a utilizar en la respuesta.
     * @return Una respuesta HTTP con una tabla HTML.
     * @throws IOException Si ocurre un error al buscar la información.
     */
    public static String getHello(String uri) throws IOException {
        String response = "HTTP/1.1 200 OK\r\n"
                + "Content-Type: application/json\r\n"
                + "\r\n" +
                "<style>\n" +
                "table, th, td {\n" +
                "  border:1px solid black;\n" +
                "}\n" +
                "</style>" +
                crearTabla(Cache.buscarTitulo(uri));
        return response;
    }

    /**
     * Define una respuesta HTML con un formulario y JavaScript.
     *
     * @return Una respuesta HTTP con un formulario HTML y código JavaScript.
     */
    public static String getIndexResponse() {
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
                "        </form> \n" + "<br>" +
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

    /**
     * Crea una tabla HTML a partir de datos proporcionados.
     *
     * @param respuestaApi Datos a utilizar para construir la tabla.
     * @return Una cadena que representa una tabla HTML.
     */
    private static String crearTabla(String respuestaApi) {
        String[] datos = respuestaApi.split(":");
        String tabla = "<table> \n";
        for (int i = 0; i < (datos.length); i++) {
            String[] respuestaTemporal = datos[i].split(",");
            tabla += "<td>" + Arrays.toString(Arrays.copyOf(respuestaTemporal, respuestaTemporal.length - 1)).replace("[", "").replace("]", "").replace("}", "") + "</td>\n</tr>\n";
            tabla += "<tr>\n<td>" + respuestaTemporal[respuestaTemporal.length - 1].replace("{", "").replace("[", "") + "</td>\n";
        }
        tabla += "</table>";
        return tabla;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    private List<Class<?>> getClasses(){
        List<Class<?>> classes = new ArrayList<>();
        try{
            for (String cp: classPaths()){
                File file = new File(cp + "/" + root);
                if(file.exists() && file.isDirectory()){
                    for (File cf: Objects.requireNonNull(file.listFiles())){
                        if(cf.isFile() && cf.getName().endsWith(".class")){
                            String rootTemp = root.replace("/",".");
                            String className = rootTemp+"."+cf.getName().replace(".class","");
                            Class<?> clasS =  Class.forName(className);
                            classes.add(clasS);
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return classes;
    }
    private ArrayList<String> classPaths(){
        String classPath = System.getProperty("java.class.path");
        String[] classPaths =  classPath.split(System.getProperty("path.separator"));
        return new ArrayList<>(Arrays.asList(classPaths));
    }
}
