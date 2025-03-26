package com.skillboostfootball.backend_main_springboot.presentation.dtos.profileSuscripciones.response;


import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class ProfileSuscripcionResponse {
    
    private Long id;
    private Long profileId;
    private Long suscripcionId;
    private String suscripcionNombre;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String status;
    private String metodoPago;
    private Integer entrenamientosDisponibles;
    private Boolean incluyeGraficas;
    private Boolean incluyeEvaluacion;

}
