package com.polideportivo.backend_springboot.domain.exception;

import org.springframework.security.core.AuthenticationException;

public class ExpiredTokenException extends AuthenticationException {
    public ExpiredTokenException(String message) {
        super(message);
    }
}