package com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.pagos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pagos")
public class PagoEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "profile_suscripcion_id", nullable = false)
    private Long profileSuscripcionId;
    
    @Column(name = "monto", precision = 10, scale = 2)
    private BigDecimal monto;
    
    @Column(name = "metodo_pago", length = 50)
    private String metodoPago;
    
    @Column(name = "status", length = 50)
    private String status;
    
    @Column(name = "referencia_externa", unique = true)
    private String referenciaExterna;
    
    @Column(name = "fecha")
    private LocalDateTime fecha;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
}
