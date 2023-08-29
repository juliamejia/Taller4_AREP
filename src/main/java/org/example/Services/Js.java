package org.example.Services;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Js implements RestServiceInterface{
    @Override
    public String getHeader() {
        return "HTTP/1.1 200 OK\r\n" +
                "Content-type: text/javascript\r\n" +
                "\r\n";
    }

    @Override
    public String getResponse() {
        byte[] contenido = new byte[0];
        try {
            Path file = Paths.get("src/main/resources/script.js");
            contenido = Files.readAllBytes(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(contenido);
    }
}
