package com.skillboostfootball.backend_main_springboot.domain.exceptions.reservas;

public class ReservaAlreadyExistsException extends RuntimeException {
    public ReservaAlreadyExistsException() {
        super("Ya estás inscrito en este entrenamiento");
    }
}
