package com.skillboostfootball.backend_main_springboot.domain.entities.graficas;

import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@Builder
public class Grafica {
    private Long id;
    private String seccion;
    private Integer nivel;
    private Integer mes;
    private Integer año;
    private Long profileId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
