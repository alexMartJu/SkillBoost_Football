package com.skillboostfootball.backend_main_springboot.domain.exceptions.entrenamientos;

public class EntrenamientoInvalidStateException extends RuntimeException {
    public EntrenamientoInvalidStateException(String message) {
        super(message);
    }
}
