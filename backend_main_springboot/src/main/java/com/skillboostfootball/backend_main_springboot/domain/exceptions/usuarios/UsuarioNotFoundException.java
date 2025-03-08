package com.skillboostfootball.backend_main_springboot.domain.exceptions.usuarios;

import com.skillboostfootball.backend_main_springboot.domain.exceptions.ResourceNotFoundException;

public class UsuarioNotFoundException extends ResourceNotFoundException {
    public UsuarioNotFoundException() {
        super("Usuario no encontrado");
    }
}
