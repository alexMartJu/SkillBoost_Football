package com.skillboostfootball.backend_main_springboot.presentation.dtos.suscripciones.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class UpdateSuscripcionPrecioRequest {
    
    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor que cero")
    private BigDecimal precio;
    
}
