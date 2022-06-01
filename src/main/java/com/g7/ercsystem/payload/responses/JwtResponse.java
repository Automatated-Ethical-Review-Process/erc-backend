package com.g7.ercsystem.payload.responses;

import lombok.Data;

import java.util.List;

@Data
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String refreshToken;
    private String id;
    private String email;
    private List<String> roles;

    public JwtResponse(String token, String refreshToken, String id, String email, List<String> roles) {
        this.token = token;
        this.refreshToken = refreshToken;
        this.id = id;
        this.email = email;
        this.roles = roles;
    }
}
