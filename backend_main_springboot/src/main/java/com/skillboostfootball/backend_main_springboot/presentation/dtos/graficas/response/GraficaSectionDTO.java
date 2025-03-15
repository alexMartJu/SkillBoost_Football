package com.skillboostfootball.backend_main_springboot.presentation.dtos.graficas.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GraficaSectionDTO {
    private String seccion;
    private Integer nivel;
}
