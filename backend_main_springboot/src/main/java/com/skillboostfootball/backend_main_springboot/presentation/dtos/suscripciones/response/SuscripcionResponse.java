package com.skillboostfootball.backend_main_springboot.presentation.dtos.suscripciones.response;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class SuscripcionResponse {

    private String nombre;
    private String slug;
    private BigDecimal precio;
    private Integer entrenamientosSemanales;
    private Boolean incluyeGraficas;
    private Boolean incluyeEvaluacion;
    private String ventaja1;
    private String ventaja2;
    private String ventaja3;
    private String ventaja4;
    
}
