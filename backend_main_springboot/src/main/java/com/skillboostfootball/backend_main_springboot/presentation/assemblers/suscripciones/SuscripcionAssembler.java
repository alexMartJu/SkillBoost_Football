package com.skillboostfootball.backend_main_springboot.presentation.assemblers.suscripciones;

import com.skillboostfootball.backend_main_springboot.domain.entities.suscripciones.Suscripcion;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.suscripciones.response.SuscripcionResponse;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.suscripciones.response.SuscripcionWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SuscripcionAssembler {
    
    public SuscripcionResponse toResponse(Suscripcion suscripcion) {
        SuscripcionResponse response = new SuscripcionResponse();
        
        response.setNombre(suscripcion.getNombre());
        response.setSlug(suscripcion.getSlug());
        response.setPrecio(suscripcion.getPrecio());
        response.setEntrenamientosSemanales(suscripcion.getEntrenamientosSemanales());
        response.setIncluyeGraficas(suscripcion.getIncluyeGraficas());
        response.setIncluyeEvaluacion(suscripcion.getIncluyeEvaluacion());
        response.setVentaja1(suscripcion.getVentaja1());
        response.setVentaja2(suscripcion.getVentaja2());
        response.setVentaja3(suscripcion.getVentaja3());
        response.setVentaja4(suscripcion.getVentaja4());
        return response;
    }
    
    public SuscripcionWrapper toWrapper(List<Suscripcion> suscripciones) {
        List<SuscripcionResponse> responseList = suscripciones.stream()
            .map(this::toResponse)
            .collect(Collectors.toList());
                
        return SuscripcionWrapper.builder()
            .suscripciones(responseList)
            .build();
    }
}
