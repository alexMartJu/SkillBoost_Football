package com.skillboostfootball.backend_payments_springboot.application.exception;

public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
