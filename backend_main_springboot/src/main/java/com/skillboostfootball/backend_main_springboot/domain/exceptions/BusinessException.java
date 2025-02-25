package com.skillboostfootball.backend_main_springboot.domain.exceptions;

public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
