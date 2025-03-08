package com.skillboostfootball.backend_main_springboot.domain.exceptions.roles;

import com.skillboostfootball.backend_main_springboot.domain.exceptions.ResourceNotFoundException;

public class RoleNotFoundException extends ResourceNotFoundException {
    public RoleNotFoundException() {
        super("Rol no encontrado");
    }
    
}
