package com.skillboostfootball.backend_main_springboot.domain.exceptions.entrenamientos;


public class EntrenamientoFullException extends RuntimeException {
    public EntrenamientoFullException() {
        super("No hay plazas disponibles para este entrenamiento");
    }
}
