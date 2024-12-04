package com.polideportivo.backend_springboot.domain.exception;

public class ResourceNotFoundException  extends BusinessException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
