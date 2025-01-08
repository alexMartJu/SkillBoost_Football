package com.polideportivo.backend_springboot.api.model.grafica;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class GraficaResponse {
    
    private Integer mes;                // Mes de la gráfica
    private List<GraficaSeccionDTO> secciones; // Lista de secciones
}
