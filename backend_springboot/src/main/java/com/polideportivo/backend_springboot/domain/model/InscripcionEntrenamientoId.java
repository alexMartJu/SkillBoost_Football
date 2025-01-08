package com.polideportivo.backend_springboot.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class InscripcionEntrenamientoId implements Serializable {
    
    @Column(name = "entrenamiento_id")
    private Long entrenamientoId;

    @Column(name = "profile_id")
    private Long profileId;
}
