package com.polideportivo.backend_springboot.api.model.entrenamiento;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EntrenamientoDataResponse {
    private Long totalEntrenamientos;
    private Integer precioMinimo;
    private Integer precioMaximo;
    private Integer duracionMinima;
    private Integer duracionMaxima;
    private Integer plazasMinimas;
    private Integer plazasMaximas;
}
