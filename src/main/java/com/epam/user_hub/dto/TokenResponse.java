package com.epam.user_hub.dto;

public class TokenResponse<T> {
    private T token;

    public TokenResponse(T token) {
        this.token = token;
    }

    public T getToken() {
        return token;
    }

    public void setToken(T token) {
        this.token = token;
    }
}