package com.skillboostfootball.backend_main_springboot.presentation.assemblers.evaluaciones;


import com.skillboostfootball.backend_main_springboot.domain.entities.evaluaciones.EvaluacionRendimiento;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.evaluaciones.response.EvaluacionRendimientoResponse;
import org.springframework.stereotype.Component;

@Component
public class EvaluacionRendimientoAssembler {
    
    public EvaluacionRendimientoResponse toResponse(EvaluacionRendimiento evaluacion) {

        EvaluacionRendimientoResponse response = new EvaluacionRendimientoResponse();
        
        response.setId(evaluacion.getId());
        response.setProfileId(evaluacion.getProfileId());
        response.setEntrenamientoId(evaluacion.getEntrenamientoId());
        response.setFechaEvaluacion(evaluacion.getFechaEvaluacion());
        response.setPuntuacion(evaluacion.getPuntuacion());
        response.setComentarios(evaluacion.getComentarios());
        response.setAreasMejora(evaluacion.getAreasMejora());
        return response;
        
    }
}
