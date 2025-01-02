package com.polideportivo.backend_springboot.domain.exception;

public class ReservaAlreadyExistsException extends BusinessException {
    public ReservaAlreadyExistsException(String message) {
        super(message);
    }
}
