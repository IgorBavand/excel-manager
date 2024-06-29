package com.arquivos.arquivos.modules.authentication.dto;

import lombok.Data;

@Data
public class TokenRefreshRequest {
    private String refreshToken;
}
