package exo1.with_builder;

import exo1.with_builder.builder.HttpRequest;
import exo1.with_builder.builder.HttpRequestBuilder;

public class Main2 {

    public static void main(String[] args) {
        
        // Requête 1 : GET
        HttpRequest req1 = new HttpRequestBuilder()
                .method("GET")
                .host("api.google.com")
                .url("/users")
                .query("limit", "10")
                .build();

        // Requête 2 : POST
        HttpRequest req2 = new HttpRequestBuilder()
                .method("POST")
                .host("api.google.com")
                .url("/login")
                .body("{\"user\":\"admin\"}")
                .keepAlive(false)
                .timeout(10000)
                .build();
                
        System.out.println(req1);
        System.out.println(req2);
    }
}