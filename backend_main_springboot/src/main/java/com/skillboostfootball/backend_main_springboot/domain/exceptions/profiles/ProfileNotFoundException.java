package com.skillboostfootball.backend_main_springboot.domain.exceptions.profiles;

import com.skillboostfootball.backend_main_springboot.domain.exceptions.ResourceNotFoundException;

public class ProfileNotFoundException extends ResourceNotFoundException {
    public ProfileNotFoundException() {
        super("Perfil no encontrado");
    }
}