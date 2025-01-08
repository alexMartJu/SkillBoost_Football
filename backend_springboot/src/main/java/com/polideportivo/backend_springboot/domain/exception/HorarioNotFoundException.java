package com.polideportivo.backend_springboot.domain.exception;

public class HorarioNotFoundException extends ResourceNotFoundException {
    public HorarioNotFoundException() {
        super("Horario No Encontrado");
    }
}
