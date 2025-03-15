package com.skillboostfootball.backend_main_springboot.domain.entities.evaluaciones;

import lombok.Builder;
import lombok.Getter;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
public class EvaluacionRendimiento {

    private Long id;
    private Long profileId;
    private Long entrenamientoId;
    private LocalDate fechaEvaluacion;
    private Integer puntuacion;
    private String comentarios;
    private String areasMejora;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    
    public void update(Integer puntuacion, String comentarios, String areasMejora) {
        this.puntuacion = puntuacion;
        this.comentarios = comentarios;
        this.areasMejora = areasMejora;
        this.updatedAt = LocalDateTime.now();
    }
    
}
