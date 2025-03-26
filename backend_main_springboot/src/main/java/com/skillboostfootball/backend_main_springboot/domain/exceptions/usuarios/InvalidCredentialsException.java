package com.skillboostfootball.backend_main_springboot.domain.exceptions.usuarios;

public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException() {
        super("Credenciales inválidas");
    }
}
