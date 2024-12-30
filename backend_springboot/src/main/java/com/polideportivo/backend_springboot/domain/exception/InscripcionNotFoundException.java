package com.polideportivo.backend_springboot.domain.exception;

public class InscripcionNotFoundException extends ResourceNotFoundException {
    public InscripcionNotFoundException() {
        super("El usuario no está inscrito en este entrenamiento.");
    }
}
