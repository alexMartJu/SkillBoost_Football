package com.skillboostfootball.backend_main_springboot.domain.entities.pagos;

import lombok.Builder;
import lombok.Getter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class Pago {
    private Long id;
    private Long profileSuscripcionId;
    private BigDecimal monto;
    private String metodoPago;
    private String status;
    private String referenciaExterna;
    private LocalDateTime fecha;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    //Métodos de dominio
    public void completar(String referenciaExterna) {
        this.status = "completed";
        this.referenciaExterna = referenciaExterna;
        this.updatedAt = LocalDateTime.now();
    }
    
    public void fallar() {
        this.status = "failed";
        this.updatedAt = LocalDateTime.now();
    }
    
    public boolean isCompleted() {
        return "completed".equals(this.status);
    }
}
