package com.polideportivo.backend_springboot.api.assembler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import com.polideportivo.backend_springboot.api.model.reserva.ReservaResponse;
import com.polideportivo.backend_springboot.domain.model.Reserva;

@Component
@RequiredArgsConstructor
public class ReservaAssembler {
    
    public ReservaResponse toResponse(Reserva reserva) {
        var response = new ReservaResponse();
        response.setPistaNombre(reserva.getPista().getNombre());
        response.setHora(reserva.getHorario().getHora());
        response.setFecha(reserva.getFecha().toString());
        return response;
    }
}
