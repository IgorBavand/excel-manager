package com.arquivos.arquivos.modules.authentication.dto;

import com.arquivos.arquivos.modules.authentication.model.User;
import lombok.Data;

@Data
public class AuthenticationResponse {
    private final String accessToken;
    private final String refreshToken;
    private final User user;

    public AuthenticationResponse(String accessToken, String refreshToken, User user) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.user = user;
    }
}
