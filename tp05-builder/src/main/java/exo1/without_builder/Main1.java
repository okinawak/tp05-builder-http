package exo1.without_builder;

import java.util.HashMap;
import java.util.Map;

public class Main1 {
	//quetion 1
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
	//Lisibilité faible : La suite de null, null, true, false ne veut rien dire à la lecture. Il faut constamment vérifier la signature du constructeur.
	//Risque d'erreur élevé : Il est très facile d'inverser deux paramètres du même type sans que le compilateur ne le signale.

	// Question 2
public static HttpRequest createRequestSecurisee(String method, String url, String host, 
                                                 String body, boolean keepAlive, boolean followRedirects) {
    // Règle 1 : Host obligatoire
    if (host == null || host.isEmpty()) {
        throw new IllegalArgumentException("Le host est obligatoire");
    }
    
    // Règle 2 : Pas de body en GET
    if ("GET".equalsIgnoreCase(method) && body != null) {
        throw new IllegalArgumentException("Une requête GET ne peut pas avoir de body");
    }
    
    // Règle 3 : keepAlive et followRedirects incompatibles
    if (keepAlive && followRedirects) {
        throw new IllegalArgumentException("keepAlive et followRedirects sont incompatibles");
    }
    
    return new HttpRequest(method, url, host, null, null, body, keepAlive, followRedirects, 5000);
}
//Pourquoi cette approche est difficile à maintenir ?

    //Découplage de la logique : La validation est séparée de l'objet. Si un autre développeur instancie HttpRequest directement via new, il contourne les règles de sécurité.

    //Duplication : Si on crée des requêtes à plusieurs endroits du code, on devra copier-coller ces conditions partout.
}