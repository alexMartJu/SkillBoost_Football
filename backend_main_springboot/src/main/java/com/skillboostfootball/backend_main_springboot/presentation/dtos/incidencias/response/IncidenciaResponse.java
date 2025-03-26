package com.skillboostfootball.backend_main_springboot.presentation.dtos.incidencias.response;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class IncidenciaResponse {
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
