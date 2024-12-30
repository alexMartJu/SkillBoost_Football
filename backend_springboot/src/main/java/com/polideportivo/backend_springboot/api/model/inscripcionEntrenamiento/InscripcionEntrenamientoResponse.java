package com.polideportivo.backend_springboot.api.model.inscripcionEntrenamiento;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
public class InscripcionEntrenamientoResponse {
    
    private String message;
    private String entrenamientoSlug;
    private String numeroSocio; 
    private LocalDateTime timestamp;
}
