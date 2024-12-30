package com.polideportivo.backend_springboot.domain.exception;

public class NoAvailableSlotsException extends BusinessException {
    public NoAvailableSlotsException() {
        super("No hay plazas disponibles para este entrenamiento.");
    }
}
