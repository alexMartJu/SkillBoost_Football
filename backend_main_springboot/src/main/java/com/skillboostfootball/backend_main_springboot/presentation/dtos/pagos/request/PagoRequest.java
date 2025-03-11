package com.skillboostfootball.backend_main_springboot.presentation.dtos.pagos.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class PagoRequest {
    
    @NotBlank(message = "El slug de la suscripción es obligatorio")
    private String suscripcionSlug;
    
    @NotNull(message = "El monto es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El monto debe ser mayor que cero")
    private BigDecimal monto;
    
    @NotBlank(message = "El método de pago es obligatorio")
    private String metodoPago;
    
    @NotBlank(message = "La referencia externa es obligatoria")
    private String referenciaExterna;
    
}
