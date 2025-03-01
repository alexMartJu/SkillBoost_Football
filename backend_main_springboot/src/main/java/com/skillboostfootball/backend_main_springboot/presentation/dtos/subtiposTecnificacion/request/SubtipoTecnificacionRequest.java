package com.skillboostfootball.backend_main_springboot.presentation.dtos.subtiposTecnificacion.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubtipoTecnificacionRequest {
    
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String nombre;
    
    @Size(max = 1000, message = "La descripción no puede exceder los 1000 caracteres")
    private String descripcion;
}
