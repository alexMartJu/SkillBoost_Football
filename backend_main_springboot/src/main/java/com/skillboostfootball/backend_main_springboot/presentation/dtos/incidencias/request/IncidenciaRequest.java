package com.skillboostfootball.backend_main_springboot.presentation.dtos.incidencias.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IncidenciaRequest {
    
    @NotBlank(message = "El tipo de incidencia es obligatorio")
    private String tipo;
    
    @NotBlank(message = "La descripción es obligatoria")
    @Size(min = 10, max = 1000, message = "La descripción debe tener entre 10 y 1000 caracteres")
    private String descripcion;
    
    private String metodoPago;
    
    private String referenciaExterna;
}
