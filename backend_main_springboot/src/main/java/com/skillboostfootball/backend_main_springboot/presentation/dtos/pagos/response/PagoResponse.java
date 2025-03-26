package com.skillboostfootball.backend_main_springboot.presentation.dtos.pagos.response;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class PagoResponse {
    private Long id;
    private Long profileSuscripcionId;
    private BigDecimal monto;
    private String metodoPago;
    private String status;
    private String referenciaExterna;
    private LocalDateTime fecha;
    
}
