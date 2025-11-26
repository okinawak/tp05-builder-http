package exo1.with_builder.builder;

import java.util.HashMap;
import java.util.Map;

public class HttpRequestBuilder {
    // Valeurs par défaut
    private String method = "GET"; // Valeur par défaut sensée
    private String url = "/";
    private String host;
    private Map<String, String> headers = new HashMap<>();
    private Map<String, String> queryParams = new HashMap<>();
    private String body;
    private boolean keepAlive = true;
    private boolean followRedirects = false;
    private int timeoutMs = 5000;

    public HttpRequestBuilder method(String method) {
        this.method = method;
        return this;
    }

    public HttpRequestBuilder url(String url) {
        this.url = url;
        return this;
    }

    public HttpRequestBuilder host(String host) {
        this.host = host;
        return this;
    }

    public HttpRequestBuilder header(String key, String value) {
        this.headers.put(key, value);
        return this;
    }

    public HttpRequestBuilder query(String key, String value) {
        this.queryParams.put(key, value);
        return this;
    }

    public HttpRequestBuilder body(String body) {
        this.body = body;
        return this;
    }

    public HttpRequestBuilder keepAlive(boolean val) {
        this.keepAlive = val;
        return this;
    }

    public HttpRequestBuilder followRedirects(boolean val) {
        this.followRedirects = val;
        return this;
    }

    public HttpRequestBuilder timeout(int ms) {
        this.timeoutMs = ms;
        return this;
    }

    public HttpRequest build() {
        // Validation 1 : Host obligatoire
        if (host == null || host.isEmpty()) {
            throw new IllegalStateException("Le host est obligatoire pour construire une requête.");
        }

        // Validation 2 : Body interdit si GET
        if ("GET".equalsIgnoreCase(method) && body != null) {
            throw new IllegalStateException("Une requête GET ne peut pas contenir de body.");
        }

        // Validation 3 : Incompatibilité keepAlive / followRedirects
        if (keepAlive && followRedirects) {
            throw new IllegalStateException("Impossible d'activer keepAlive et followRedirects simultanément.");
        }

        return new HttpRequest(method, url, host, headers, queryParams, body, keepAlive, followRedirects, timeoutMs);
    }
}