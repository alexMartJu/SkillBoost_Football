package com.polideportivo.backend_springboot.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pista_privadas")
public class PistaPrivada {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 191)
    private String nombre;

    @Column(name = "slug", nullable = false, unique = true, length = 191)
    private String slug;

    @Column(name = "info", nullable = true)
    private String info;

    @ManyToMany(mappedBy = "pistaPrivadas")
    private List<Deporte> deportes;

    @OneToMany(mappedBy = "pistaPrivada", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Entrenamiento> entrenamientos;

    @Transient 
    private List<Image> images;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
}
