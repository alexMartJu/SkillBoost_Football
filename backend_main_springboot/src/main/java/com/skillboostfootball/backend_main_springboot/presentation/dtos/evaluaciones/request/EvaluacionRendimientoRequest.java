package com.skillboostfootball.backend_main_springboot.presentation.dtos.evaluaciones.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EvaluacionRendimientoRequest {
    
    @NotNull(message = "La puntuación es obligatoria")
    @Min(value = 1, message = "La puntuación mínima es 1")
    @Max(value = 100, message = "La puntuación máxima es 100")
    private Integer puntuacion;
    
    @NotBlank(message = "Los comentarios son obligatorios")
    private String comentarios;
    
    @NotBlank(message = "Las áreas de mejora son obligatorias")
    private String areasMejora;
    
}
