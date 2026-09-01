package com.jmfashions.webapp.DTO;



public class LoginResponse {

    private String message;
    private String name;

    public LoginResponse(String message, String name) {
        this.message = message;
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public String getName() {
        return name;
    }
}
