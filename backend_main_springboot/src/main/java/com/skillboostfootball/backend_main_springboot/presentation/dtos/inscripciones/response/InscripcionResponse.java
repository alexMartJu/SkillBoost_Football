package com.skillboostfootball.backend_main_springboot.presentation.dtos.inscripciones.response;

import com.skillboostfootball.backend_main_springboot.presentation.dtos.entrenamientos.response.EntrenamientoResponse;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.evaluaciones.response.EvaluacionRendimientoResponse;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.profiles.response.ProfileResponse;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class InscripcionResponse {
    private Long id;
    private ProfileResponse profile;
    private EntrenamientoResponse entrenamiento;
    private LocalDateTime fecha;
    private EvaluacionRendimientoResponse evaluacion;
}
