package com.skillboostfootball.backend_main_springboot.presentation.dtos.entrenamientos.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreateEntrenamientoRequest {
    
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 255, message = "El nombre debe tener entre 3 y 255 caracteres")
    private String nombre;
    
    @Size(max = 1000, message = "La descripción no puede exceder los 1000 caracteres")
    private String descripcion;
    
    @NotBlank(message = "El nivel es obligatorio")
    private String nivel;
    
    private Integer edadMinima;
    private Integer edadMaxima;
    private String tecnificacionSlug;
    private String subtipoTecnificacionSlug;
    
    @NotBlank(message = "El slug de la pista es obligatorio")
    private String pistaSlug;
    
    @NotNull(message = "La fecha de inicio es obligatoria")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaInicio;
    
    @NotNull(message = "La fecha de fin es obligatoria")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaFin;
    
    @NotNull(message = "El número máximo de plazas es obligatorio")
    @Min(value = 1, message = "El número máximo de plazas debe ser al menos 1")
    private Integer maxPlazas;
    
    private String objetivos;
    private String equipamientoNecesario;
    private Integer duracionMinutos;
    
}
