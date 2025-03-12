package com.skillboostfootball.backend_main_springboot.domain.exceptions.entrenamientos;

public class AgeNotAllowedException extends RuntimeException {
    public AgeNotAllowedException() {
        super("Tu edad no cumple con los requisitos para este entrenamiento");
    }
}
