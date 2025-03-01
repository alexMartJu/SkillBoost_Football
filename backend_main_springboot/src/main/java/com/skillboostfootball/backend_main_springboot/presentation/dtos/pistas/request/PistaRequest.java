package com.skillboostfootball.backend_main_springboot.presentation.dtos.pistas.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PistaRequest {
    
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String nombre;
    
    @Size(max = 1000, message = "La descripción no puede exceder los 1000 caracteres")
    private String descripcion;
    
    @NotBlank(message = "El tipo es obligatorio")
    @Size(max = 50, message = "El tipo no puede exceder los 50 caracteres")
    private String tipo;
    
    @Size(max = 50, message = "Las dimensiones no pueden exceder los 50 caracteres")
    private String dimensiones;
    
    @NotNull(message = "La capacidad es obligatoria")
    @Min(value = 1, message = "La capacidad debe ser mayor que 0")
    private Integer capacidad;

}
