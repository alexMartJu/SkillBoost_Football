package com.polideportivo.backend_springboot.domain.exception;

public class ProfileNotFoundException extends ResourceNotFoundException {
    
    public ProfileNotFoundException() {
        super("Profile No Encontrado");
    }
}
