package com.skillboostfootball.backend_main_springboot.domain.entities.suscripciones;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class Suscripcion {
    private Long id;
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
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    public void updatePrecio(BigDecimal precio) {
        this.precio = precio;
        this.updatedAt = LocalDateTime.now();
    }
}
