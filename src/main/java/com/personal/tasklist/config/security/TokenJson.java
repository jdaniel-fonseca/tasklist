package com.personal.tasklist.config.security;

import lombok.Data;

@Data
public class TokenJson {

    private String token;

    public TokenJson(String token) {
        this.token = token;
    }

}
