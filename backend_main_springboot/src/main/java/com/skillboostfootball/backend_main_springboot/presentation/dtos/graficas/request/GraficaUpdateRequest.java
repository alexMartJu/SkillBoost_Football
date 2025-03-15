package com.skillboostfootball.backend_main_springboot.presentation.dtos.graficas.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GraficaUpdateRequest {
    
    @Valid
    private List<GraficaSeccionRequest> secciones;
    
    @Getter
    @Setter
    public static class GraficaSeccionRequest {
        
        @NotBlank(message = "La sección es obligatoria")
        private String seccion;
        
        @NotNull(message = "El nivel es obligatorio")
        @Min(value = 1, message = "El nivel mínimo es 1")
        @Max(value = 100, message = "El nivel máximo es 100")
        private Integer nivel;
    }
}
