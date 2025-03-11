package com.skillboostfootball.backend_main_springboot.presentation.assemblers.pagos;

import com.skillboostfootball.backend_main_springboot.domain.entities.pagos.Pago;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.pagos.response.PagoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PagoAssembler {
    
    public PagoResponse toResponse(Pago pago) {

        PagoResponse response = new PagoResponse();
        
        response.setId(pago.getId());
        response.setProfileSuscripcionId(pago.getProfileSuscripcionId());
        response.setMonto(pago.getMonto());
        response.setMetodoPago(pago.getMetodoPago());
        response.setStatus(pago.getStatus());
        response.setReferenciaExterna(pago.getReferenciaExterna());
        response.setFecha(pago.getFecha());
        return response;
        
    }
}
