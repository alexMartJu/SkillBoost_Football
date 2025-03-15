package com.skillboostfootball.backend_main_springboot.presentation.dtos.graficas.response;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class GraficaResponse {
    private Integer mes;
    private List<GraficaSectionDTO> secciones;
}
