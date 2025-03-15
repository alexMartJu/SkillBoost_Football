package com.skillboostfootball.backend_main_springboot.domain.entities.logros;

import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@Builder
public class Logro {

    private Long id;
    private String nombre;
    private String slug;
    private String descripcion;
    private Integer requisitoEntrenamientos;
    private String nivelDificultad;
    private String recompensa;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    
}