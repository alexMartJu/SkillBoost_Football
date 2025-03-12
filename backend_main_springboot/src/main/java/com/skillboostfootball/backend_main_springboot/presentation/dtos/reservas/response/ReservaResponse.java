package com.skillboostfootball.backend_main_springboot.presentation.dtos.reservas.response;

import com.skillboostfootball.backend_main_springboot.presentation.dtos.entrenamientos.response.EntrenamientoResponse;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReservaResponse {
    private Long id;
    private EntrenamientoResponse entrenamiento;
    private LocalDateTime fecha;
}
