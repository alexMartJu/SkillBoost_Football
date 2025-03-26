package com.skillboostfootball.backend_main_springboot.domain.exceptions.tecnificaciones;

import com.skillboostfootball.backend_main_springboot.domain.exceptions.ResourceNotFoundException;

public class TecnificacionNotFoundException extends ResourceNotFoundException {
    public TecnificacionNotFoundException() {
        super("Tecnificación No Encontrada");
    }
}
