package com.skillboostfootball.backend_main_springboot.domain.entities.horarios;

import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@Builder
public class Horario {
    private Long id;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private Boolean disponible;
}
