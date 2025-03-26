package com.skillboostfootball.backend_main_springboot.presentation.dtos.profiles.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProfileResponse {
    private String numeroSocio;
    private String nombre;
    private String apellidos;
    private String bio;
    private String image;
    private Integer edad;
    private Boolean esMenor;
    private Integer entrenamientosDisponibles;
    
    // Campos para jugadores de club
    private String posicionPreferida;
    private String clubOrigen;
    
    // Campos para entrenadores
    private String numeroEntrenador;
    private String especialidad;
    private Integer experienciaAnios;
    private String certificaciones;
    
    // Campos para jugadores sociales
    private String organizacionOrigen;

    //Información de suscripción activa
    private String suscripcionNombre;
    private String suscripcionSlug;
    private Boolean incluyeGraficas;
    private Boolean incluyeEvaluacion;
    private String suscripcionStatus;
    private String fechaFinSuscripcion;
}
