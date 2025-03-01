package com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.pistas;

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
@Table(name = "pistas")
public class PistaEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", length = 191, nullable = false)
    private String nombre;

    @Column(name = "slug", length = 191, unique = true, nullable = false)
    private String slug;
    
    @Column(name = "descripcion", length = 1000)
    private String descripcion;
    
    @Column(name = "tipo", length = 50)
    private String tipo;
    
    @Column(name = "dimensiones", length = 50)
    private String dimensiones;
    
    @Column(name = "capacidad")
    private Integer capacidad;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
    
}
