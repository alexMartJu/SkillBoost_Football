package com.skillboostfootball.backend_main_springboot.presentation.assemblers.horarios;

import com.skillboostfootball.backend_main_springboot.domain.entities.horarios.Horario;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.horarios.response.HorarioResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HorarioAssembler {
    
    public HorarioResponse toResponse(Horario horario) {
        if (horario == null) {
            return null;
        }
        HorarioResponse response = new HorarioResponse();
        response.setId(horario.getId());
        response.setFechaInicio(horario.getFechaInicio());
        response.setFechaFin(horario.getFechaFin());
        response.setDisponible(horario.getDisponible());
        
        return response;
    }
}
