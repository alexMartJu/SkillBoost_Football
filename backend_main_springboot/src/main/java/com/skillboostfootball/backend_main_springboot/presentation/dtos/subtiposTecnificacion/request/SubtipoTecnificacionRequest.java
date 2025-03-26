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

    @Size(max = 100, message = "El tipo de entrenamiento no puede exceder los 100 caracteres")
    private String tipoEntrenamiento;
    
    @Size(max = 1000, message = "Los objetivos no pueden exceder los 1000 caracteres")
    private String objetivos;
    
    @Size(max = 1000, message = "Los beneficios no pueden exceder los 1000 caracteres")
    private String beneficios;
    
    @Size(max = 100, message = "La frecuencia sugerida no puede exceder los 100 caracteres")
    private String frecuenciaSugerida;
    
    @Size(max = 255, message = "El método de evaluación no puede exceder los 255 caracteres")
    private String metodoEvaluacion;
    
    @Size(max = 255, message = "La tecnología utilizada no puede exceder los 255 caracteres")
    private String tecnologiaUtilizada;
}
