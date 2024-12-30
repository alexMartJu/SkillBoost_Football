package com.polideportivo.backend_springboot.api.assembler;

import com.polideportivo.backend_springboot.api.model.pistaPrivada.PistaPrivadaResponse;
import com.polideportivo.backend_springboot.domain.model.PistaPrivada;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PistaPrivadaAssembler {
    
    public PistaPrivadaResponse toResponse(PistaPrivada pistaPrivada) {
        return PistaPrivadaResponse.builder()
                .nombre(pistaPrivada.getNombre())
                .slug(pistaPrivada.getSlug())
                .info(pistaPrivada.getInfo())
                .build();
    }
}
