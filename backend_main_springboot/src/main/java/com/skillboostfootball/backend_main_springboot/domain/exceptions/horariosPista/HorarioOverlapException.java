package com.skillboostfootball.backend_main_springboot.domain.exceptions.horariosPista;

import com.skillboostfootball.backend_main_springboot.domain.exceptions.BusinessException;

public class HorarioOverlapException extends BusinessException {
    public HorarioOverlapException() {
        super("El horario solicitado se solapa con otro entrenamiento existente");
    }
}
