package com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.subtiposTecnificacion;

import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.tecnificaciones.TecnificacionEntity;
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
@Table(name = "subtipo_tecnificacion")
public class SubtipoTecnificacionEntity {
       
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", length = 191, nullable = false)
    private String nombre;

    @Column(name = "slug", length = 191, unique = true, nullable = false)
    private String slug;
    
    @Column(name = "descripcion", length = 1000)
    private String descripcion;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tecnificacion_id")
    private TecnificacionEntity tecnificacion;

    @Column(name = "tipo_entrenamiento", length = 100)
    private String tipoEntrenamiento;
    
    @Column(name = "objetivos", columnDefinition = "TEXT")
    private String objetivos;
    
    @Column(name = "beneficios", columnDefinition = "TEXT")
    private String beneficios;
    
    @Column(name = "frecuencia_sugerida", length = 100)
    private String frecuenciaSugerida;
    
    @Column(name = "metodo_evaluacion", length = 255)
    private String metodoEvaluacion;
    
    @Column(name = "tecnologia_utilizada", length = 255)
    private String tecnologiaUtilizada;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
}
