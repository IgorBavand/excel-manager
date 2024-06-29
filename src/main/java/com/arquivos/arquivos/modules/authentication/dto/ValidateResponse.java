package com.arquivos.arquivos.modules.authentication.dto;

import java.util.List;

public class ValidateResponse {
    private boolean valid;
    private String username;
    private List<String> roles;

    public ValidateResponse(boolean valid, String username, List<String> roles) {
        this.valid = valid;
        this.username = username;
        this.roles = roles;
    }

    // Getters and Setters
}