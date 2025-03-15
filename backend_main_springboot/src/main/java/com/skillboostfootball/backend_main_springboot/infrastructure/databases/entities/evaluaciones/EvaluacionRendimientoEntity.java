package com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.evaluaciones;

import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.entrenamientos.EntrenamientoEntity;
import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.profiles.ProfileEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "evaluaciones_rendimiento")
public class EvaluacionRendimientoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private ProfileEntity profile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entrenamiento_id")
    private EntrenamientoEntity entrenamiento;

    @Column(name = "fecha_evaluacion")
    private LocalDate fechaEvaluacion;

    @Column(name = "puntuacion")
    private Integer puntuacion;

    @Column(name = "comentarios")
    private String comentarios;

    @Column(name = "areas_mejora")
    private String areasMejora;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

}
