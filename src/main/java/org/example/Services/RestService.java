package org.example.Services;
import org.example.HttpServer;
import org.example.spark.Spark;

import java.io.IOException;

public class RestService {
    public static void main(String[] args) throws IOException{
        HttpServer server = HttpServer.getInstance();
        Spark.get("",(req, res)->{
            res.setType("application/json");
            return res.getResponse();
        });
        server.main(args);
    }
}