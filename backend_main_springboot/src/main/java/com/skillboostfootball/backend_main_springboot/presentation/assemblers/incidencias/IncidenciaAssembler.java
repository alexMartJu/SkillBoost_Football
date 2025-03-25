package com.skillboostfootball.backend_main_springboot.presentation.assemblers.incidencias;

import com.skillboostfootball.backend_main_springboot.domain.entities.incidencias.Incidencia;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.incidencias.response.IncidenciaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IncidenciaAssembler {
    
    public IncidenciaResponse toResponse(Incidencia incidencia) {
        IncidenciaResponse response = new IncidenciaResponse();
        
        response.setId(incidencia.getId());
        response.setUsuarioId(incidencia.getUsuarioId());
        response.setTipo(incidencia.getTipo());
        response.setDescripcion(incidencia.getDescripcion());
        response.setMetodoPago(incidencia.getMetodoPago());
        response.setReferenciaExterna(incidencia.getReferenciaExterna());
        response.setStatus(incidencia.getStatus());
        response.setFechaIncidencia(incidencia.getFechaIncidencia());
        response.setCreatedAt(incidencia.getCreatedAt());
        response.setUpdatedAt(incidencia.getUpdatedAt());
        
        return response;
    }
}
