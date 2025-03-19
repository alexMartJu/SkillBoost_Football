package com.skillboostfootball.backend_main_springboot.domain.exceptions.organizaciones;

import com.skillboostfootball.backend_main_springboot.domain.exceptions.TakenException;

public class OrganizacionNameTakenException extends TakenException {
    public OrganizacionNameTakenException() {
        super("El nombre de la organización ya está en uso");
    }
}
