package org.example.controller;

import org.example.controller.annotations.Component;
import org.example.controller.annotations.RequestMapping;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class WebController {
    @RequestMapping(value = "/index")
    public static String index() {
        return "HTTP/1.1 200 OK\r\n" +
                "Content-type: text/html\r\n" +
                "\r\n" + body("index.html");
    }

    private static String body(String extention){
        byte[] content = new byte[0];
        try {
            Path file = Paths.get("src/main/resources/"+extention);
            content = Files.readAllBytes(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(content);
    }

    @RequestMapping(value = "/Estilo.css")
    public static String indexcss() {
        return "HTTP/1.1 200 OK\r\n" +
                "Content-type: text/css\r\n" +
                "\r\n" + body("index.css");
    }

    @RequestMapping(value = "/index.js")
    public static String indexjs() {
        return "HTTP/1.1 200 OK\r\n" +
                "Content-type: text/javascript\r\n" +
                "\r\n" + body("script.js");
    }
}