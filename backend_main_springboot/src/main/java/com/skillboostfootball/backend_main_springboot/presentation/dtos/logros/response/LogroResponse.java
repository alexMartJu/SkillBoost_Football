package com.skillboostfootball.backend_main_springboot.presentation.dtos.logros.response;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class LogroResponse {
    private Long id;
    private String nombre;
    private String descripcion;
    private Integer requisitoEntrenamientos;
    private String nivelDificultad;
    private String recompensa;
    private Integer progreso;
    private Boolean completado;
    private LocalDateTime fechaLogro;
}
