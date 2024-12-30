package com.polideportivo.backend_springboot.api.assembler;

import com.polideportivo.backend_springboot.domain.model.InscripcionEntrenamiento;
import com.polideportivo.backend_springboot.api.model.inscripcionEntrenamiento.InscripcionEntrenamientoResponse;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class InscripcionEntrenamientoAssembler {
    
    public InscripcionEntrenamientoResponse toResponse(InscripcionEntrenamiento inscripcion) {
        return new InscripcionEntrenamientoResponse(
            "Inscripción realizada con éxito",
            inscripcion.getEntrenamiento().getSlug(),
            inscripcion.getProfile().getNumeroSocio(),
            inscripcion.getCreatedAt()
        );
    }

    public InscripcionEntrenamientoResponse toDesinscripcionResponse(String slug, String numeroSocio) {
        return new InscripcionEntrenamientoResponse(
            "Desinscripción realizada con éxito",
            slug,
            numeroSocio, 
            LocalDateTime.now()
        );
    }
}
