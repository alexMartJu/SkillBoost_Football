package com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.incidencias;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "incidencias")
public class IncidenciaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;
    
    @Column(name = "tipo", nullable = false)
    private String tipo;
    
    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;
    
    @Column(name = "metodo_pago")
    private String metodoPago;
    
    @Column(name = "referencia_externa")
    private String referenciaExterna;
    
    @Column(name = "status", nullable = false)
    private String status;
    
    @Column(name = "fecha_incidencia", nullable = false)
    private LocalDateTime fechaIncidencia;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
