package com.skillboostfootball.backend_main_springboot.domain.entities.incidencias;

import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@Builder
public class Incidencia {
    private Long id;
    private Long usuarioId;
    private String tipo;
    private String descripcion;
    private String metodoPago;
    private String referenciaExterna;
    private String status;
    private LocalDateTime fechaIncidencia;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
