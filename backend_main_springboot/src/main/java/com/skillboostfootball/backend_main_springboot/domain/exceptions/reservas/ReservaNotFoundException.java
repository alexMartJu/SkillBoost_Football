package com.skillboostfootball.backend_main_springboot.domain.exceptions.reservas;

import com.skillboostfootball.backend_main_springboot.domain.exceptions.ResourceNotFoundException;

public class ReservaNotFoundException extends ResourceNotFoundException {
    public ReservaNotFoundException() {
        super("Reserva no encontrada");
    }
}
