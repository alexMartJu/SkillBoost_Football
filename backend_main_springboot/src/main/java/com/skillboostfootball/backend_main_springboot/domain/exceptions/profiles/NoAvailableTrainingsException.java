package com.skillboostfootball.backend_main_springboot.domain.exceptions.profiles;

public class NoAvailableTrainingsException extends RuntimeException {
    public NoAvailableTrainingsException() {
        super("No tienes entrenamientos disponibles");
    }
}
