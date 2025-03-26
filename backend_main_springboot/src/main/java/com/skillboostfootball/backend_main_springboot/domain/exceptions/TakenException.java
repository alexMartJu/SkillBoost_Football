package com.skillboostfootball.backend_main_springboot.domain.exceptions;

public class TakenException extends BusinessException {
    public TakenException(String message) {
        super(message);
    }
}
