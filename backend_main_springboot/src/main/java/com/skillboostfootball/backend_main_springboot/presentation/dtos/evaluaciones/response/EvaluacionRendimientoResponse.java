package com.skillboostfootball.backend_main_springboot.presentation.dtos.evaluaciones.response;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class EvaluacionRendimientoResponse {
    private Long id;
    private Long profileId;
    private Long entrenamientoId;
    private LocalDate fechaEvaluacion;
    private Integer puntuacion;
    private String comentarios;
    private String areasMejora;
}
