package com.polideportivo.backend_springboot.domain.exception;

public class EmailTakenException extends TakenException{
    
    public EmailTakenException() {
        super("Email has already been taken");
    }
}
