package com.polideportivo.backend_springboot.domain.exception;

public class EmailNotFoundException extends ResourceNotFoundException {
    public EmailNotFoundException() {
        super("Email Not Found");
    }
}
