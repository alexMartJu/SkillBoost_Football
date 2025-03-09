package com.skillboostfootball.backend_main_springboot.presentation.dtos.horariosPista.response;

import com.skillboostfootball.backend_main_springboot.presentation.dtos.pistas.response.PistaResponse;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class HorarioPistaResponse {
    private Long id;
    private PistaResponse pista;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private Long entrenamientoId;
    private Boolean disponible;
}
