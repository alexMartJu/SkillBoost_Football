package com.polideportivo.backend_springboot.domain.exception;

public class InscripcionAlreadyExistsException extends RuntimeException {
    public InscripcionAlreadyExistsException(String message) {
        super(message);
    }
}
