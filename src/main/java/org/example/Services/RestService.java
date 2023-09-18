package org.example.Services;
import org.example.HttpServer;
import org.example.spark.Spark;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class RestService {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        HttpServer server = HttpServer.getInstance();
        server.run(args);
    }
}