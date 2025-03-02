package com.skillboostfootball.backend_main_springboot.presentation.dtos.entrenamientos.request;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntrenamientoFilterRequest {
    private String nombre;
    private String nivel;
    private Integer edadMinima;
    private Integer edadMaxima;
    private Integer maxPlazasMin;
    private Integer maxPlazasMax;
    private String tecnificacionNombre;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaInicio;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaFin;
}