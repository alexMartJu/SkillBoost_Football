package com.skillboostfootball.backend_main_springboot.domain.exceptions.auth;

public class TokenExpiredException extends RuntimeException {
    public TokenExpiredException() {
        super("Token expired");
    }
}
