package com.polideportivo.backend_springboot.domain.exception;

public class NumeroSocioTakenException extends TakenException{
    
    public NumeroSocioTakenException() {
        super("NumeroSocio has already been taken");
    }
}
