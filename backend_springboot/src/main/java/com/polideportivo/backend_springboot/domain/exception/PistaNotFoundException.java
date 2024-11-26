package com.polideportivo.backend_springboot.domain.exception;

public class PistaNotFoundException extends ResourceNotFoundException {
    public PistaNotFoundException() {
        super("Pista Not Found");
    }
}
