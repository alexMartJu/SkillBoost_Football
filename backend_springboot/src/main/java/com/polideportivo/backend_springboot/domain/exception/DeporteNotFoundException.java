package com.polideportivo.backend_springboot.domain.exception;

public class DeporteNotFoundException  extends ResourceNotFoundException {
    public DeporteNotFoundException() {
        super("Deporte Not Found");
    }
}
