package org.example.Services;
import org.example.HttpServer;
import java.io.IOException;

public class RestService {

    public static void main(String[] args) throws IOException{
        HttpServer server = HttpServer.getInstance();
        server.addServices("/index.html", new Html());
        server.addServices("/script.js", new Js());
        server.addServices("/Estilo.css", new Css());
        server.addServices("/imagenJulia.jpg", new Jpg());
        server.main(args);
    }
}