package com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.suscripciones;

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
@Table(name = "suscripciones")
public class SuscripcionEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", length = 100)
    private String nombre;
    
    @Column(name = "slug", length = 255, unique = true, nullable = false)
    private String slug;

    @Column(name = "precio", precision = 10, scale = 2)
    private BigDecimal precio;

    @Column(name = "entrenamientos_totales")
    private Integer entrenamientosTotales;

    @Column(name = "incluye_graficas")
    private Boolean incluyeGraficas;
    
    @Column(name = "incluye_evaluacion")
    private Boolean incluyeEvaluacion;
    
    @Column(name = "ventaja1", length = 255)
    private String ventaja1;
    
    @Column(name = "ventaja2", length = 255)
    private String ventaja2;
    
    @Column(name = "ventaja3", length = 255)
    private String ventaja3;
    
    @Column(name = "ventaja4", length = 255)
    private String ventaja4;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
}
