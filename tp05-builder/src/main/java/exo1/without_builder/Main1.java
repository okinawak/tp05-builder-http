package exo1.without_builder;

import java.util.HashMap;
import java.util.Map;

public class Main1 {
    public static void main(String[] args) {
        
        // Requête 1 : GET /users?limit=10
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("limit", "10");
        
        
        HttpRequest req1 = new HttpRequest(
            "GET", 
            "/users", 
            "api.mon-site.com", 
            null, 
            queryParams, 
            null, 
            true, 
            false, 
            10000
        );

        // Requête 2 : POST /login avec un body JSON
        String jsonBody = "{\"username\": \"user1\", \"password\": \"pass123\"}";
        
        HttpRequest req2 = new HttpRequest(
            "POST", 
            "/login", 
            "api.mon-site.com", 
            null, 
            null, 
            jsonBody, 
            false, 
            true, 
            10000
        );
        
        System.out.println(req1);
        System.out.println(req2);
    }
}