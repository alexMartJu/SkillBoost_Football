package com.skillboostfootball.backend_main_springboot.domain.exceptions.pistas;

import com.skillboostfootball.backend_main_springboot.domain.exceptions.ResourceNotFoundException;

public class PistaNotFoundException extends ResourceNotFoundException {
    public PistaNotFoundException() {
        super("Pista No Encontrada");
    }
}
