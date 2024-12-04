package com.polideportivo.backend_springboot.domain.exception;

public class EntrenamientoNotFoundException extends ResourceNotFoundException {
    public EntrenamientoNotFoundException() {
        super("Entrenamiento Not Found");
    }
}
