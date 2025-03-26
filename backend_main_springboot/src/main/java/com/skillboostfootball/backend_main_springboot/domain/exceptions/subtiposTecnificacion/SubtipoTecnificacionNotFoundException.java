package com.skillboostfootball.backend_main_springboot.domain.exceptions.subtiposTecnificacion;

import com.skillboostfootball.backend_main_springboot.domain.exceptions.ResourceNotFoundException;

public class SubtipoTecnificacionNotFoundException extends ResourceNotFoundException  {
    public SubtipoTecnificacionNotFoundException() {
        super("Subtipo No Encontrado");
    }
}
