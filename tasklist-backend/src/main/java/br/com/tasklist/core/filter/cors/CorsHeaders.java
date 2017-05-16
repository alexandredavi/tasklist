package br.com.tasklist.core.filter.cors;

public enum CorsHeaders {
    
    ACCESS_CONTROL_ALLOW_ORIGIN("Access-Control-Allow-Origin"),
    ACCESS_CONTROL_ALLOW_METHODS("Access-Control-Allow-Methods"),
    ACCESS_CONTROL_ALLOW_HEADERS("Access-Control-Allow-Headers"),
    ACCESS_CONTROL_REQUEST_METHOD("Access-Control-Request-Method"),
    ACCESS_CONTROL_REQUEST_HEADERS("Access-Control-Request-Headers"),
    ORIGIN("Origin");

    private String name;
    
    CorsHeaders(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return name;
    }
    
}