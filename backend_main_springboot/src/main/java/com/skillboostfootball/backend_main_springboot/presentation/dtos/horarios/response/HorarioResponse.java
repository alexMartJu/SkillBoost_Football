package com.skillboostfootball.backend_main_springboot.presentation.dtos.horarios.response;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class HorarioResponse {
    private Long id;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private Boolean disponible;
}
