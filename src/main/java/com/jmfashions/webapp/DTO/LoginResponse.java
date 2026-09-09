package com.jmfashions.webapp.DTO;

public class LoginResponse {

    private String message;
    private String name;
    private Long id;

    public LoginResponse(String message, String name, Long id) {
        this.message = message;
        this.name = name;
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }
}
