package com.polideportivo.backend_springboot.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "entrenamiento_profile")
public class InscripcionEntrenamiento {
    
    @EmbeddedId
    private InscripcionEntrenamientoId id;

    @ManyToOne
    @JoinColumn(name = "profile_id", insertable = false, updatable = false)
    private Profile profile; 

    @ManyToOne
    @JoinColumn(name = "entrenamiento_id", insertable = false, updatable = false)
    private Entrenamiento entrenamiento; 

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
