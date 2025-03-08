package com.skillboostfootball.backend_main_springboot.presentation.dtos.entrenadores.response;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;

@Getter
@Setter
@Builder
public class EntrenadorResponse {

    private Long id;
    private String numeroEntrenador;
    private String nombre;
    private String apellidos;
    private String email;
    private String especialidad;
    private Integer experienciaAnios;
    private String certificaciones;
    private String image;
    
}
