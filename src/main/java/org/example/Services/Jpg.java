package org.example.Services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Jpg implements RestServiceInterface{
    @Override
    public String getHeader() {
        return "HTTP/1.1 200 OK\r\n" +
                "Content-type: image/jpg\r\n" +
                "\r\n";
    }

    @Override
    public String getResponse() {
        String contenido = null;
        try {
            Path file = new File("src/main/resources/stich.png").toPath();
            contenido = Files.probeContentType(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contenido;
    }
}
