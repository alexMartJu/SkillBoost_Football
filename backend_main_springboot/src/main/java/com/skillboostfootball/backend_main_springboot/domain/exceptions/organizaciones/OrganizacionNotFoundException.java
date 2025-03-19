package com.skillboostfootball.backend_main_springboot.domain.exceptions.organizaciones;

import com.skillboostfootball.backend_main_springboot.domain.exceptions.ResourceNotFoundException;

public class OrganizacionNotFoundException extends ResourceNotFoundException {
    public OrganizacionNotFoundException() {
        super("Organización no encontrada");
    }
}
