package com.skillboostfootball.backend_main_springboot.presentation.dtos.horariosPista.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class HorarioOcupadoResponse {

    private Long id;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    
}
