package com.skillboostfootball.backend_main_springboot.presentation.assemblers.horariosPista;


import com.skillboostfootball.backend_main_springboot.domain.entities.horariosPista.HorarioPista;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.horariosPista.response.HorarioPistaResponse;
import com.skillboostfootball.backend_main_springboot.presentation.assemblers.pistas.PistaAssembler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HorarioPistaAssembler {
    
    private final PistaAssembler pistaAssembler;
    
    public HorarioPistaResponse toResponse(HorarioPista horarioPista) {
        if (horarioPista == null) {
            return null;
        }
        
        HorarioPistaResponse response = new HorarioPistaResponse();
        response.setId(horarioPista.getId());
        
        if (horarioPista.getPista() != null) {
            response.setPista(pistaAssembler.toResponse(horarioPista.getPista()));
        }
        
        response.setFechaInicio(horarioPista.getFechaInicio());
        response.setFechaFin(horarioPista.getFechaFin());
        
        if (horarioPista.getEntrenamiento() != null) {
            response.setEntrenamientoId(horarioPista.getEntrenamiento().getId());
        }
        
        // Calculamos disponibilidad (true si no hay entrenamiento asignado)
        response.setDisponible(horarioPista.getEntrenamiento() == null);
        
        return response;
    }
}
