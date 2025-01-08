package com.polideportivo.backend_springboot.domain.exception;

public class ReservaNotFoundException extends ResourceNotFoundException {
    public ReservaNotFoundException() {
        super("Reserva No Encontrada");
    }
}
