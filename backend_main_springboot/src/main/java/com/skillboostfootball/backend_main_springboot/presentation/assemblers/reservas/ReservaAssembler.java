package com.skillboostfootball.backend_main_springboot.presentation.assemblers.reservas;

import com.skillboostfootball.backend_main_springboot.domain.entities.reservas.Reserva;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.reservas.response.ReservaResponse;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.reservas.response.ReservaWrapper;
import com.skillboostfootball.backend_main_springboot.presentation.assemblers.entrenamientos.EntrenamientoAssembler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ReservaAssembler {
    private final EntrenamientoAssembler entrenamientoAssembler;
    
    public ReservaResponse toResponse(Reserva reserva) {
        ReservaResponse response = new ReservaResponse();
        
        response.setId(reserva.getId());
        response.setFecha(reserva.getFecha());
        
        if (reserva.getEntrenamiento() != null) {
            response.setEntrenamiento(entrenamientoAssembler.toResponse(reserva.getEntrenamiento()));
        }
        
        return response;
    }
    
    public ReservaWrapper toWrapper(List<Reserva> reservas) {
        List<ReservaResponse> responseList = reservas.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
                
        return ReservaWrapper.builder()
                .reservas(responseList)
                .build();
    }
}
