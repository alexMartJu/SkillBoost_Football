package com.skillboostfootball.backend_main_springboot.domain.exceptions.evaluaciones;

import com.skillboostfootball.backend_main_springboot.domain.exceptions.ResourceNotFoundException;

public class EvaluacionNotFoundException extends ResourceNotFoundException {
    public EvaluacionNotFoundException() {
        super("Evaluación de rendimiento no encontrada");
    }
}
