package com.skillboostfootball.backend_main_springboot.domain.exceptions.suscripciones;

import com.skillboostfootball.backend_main_springboot.domain.exceptions.ResourceNotFoundException;

public class SuscripcionNotFoundException extends ResourceNotFoundException {
    public SuscripcionNotFoundException() {
        super("Suscripción No Encontrada");
    }
}
