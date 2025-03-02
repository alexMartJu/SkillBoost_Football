package com.skillboostfootball.backend_main_springboot.domain.exceptions.entrenamientos;

import com.skillboostfootball.backend_main_springboot.domain.exceptions.ResourceNotFoundException;

public class EntrenamientoNotFoundException extends ResourceNotFoundException {
    public EntrenamientoNotFoundException() {
        super("Entrenamiento No Encontrado");
    }
}
