package com.skillboostfootball.backend_main_springboot.domain.exceptions.usuarios;

import com.skillboostfootball.backend_main_springboot.domain.exceptions.TakenException;

public class EmailTakenException extends TakenException {
    
    public EmailTakenException() {
        super("El email ya está ocupado");
    }
}
