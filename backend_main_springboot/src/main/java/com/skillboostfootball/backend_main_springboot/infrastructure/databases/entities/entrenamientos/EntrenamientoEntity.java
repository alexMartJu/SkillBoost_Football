package com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.entrenamientos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.horariosPista.HorarioPistaEntity;
import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.subtiposTecnificacion.SubtipoTecnificacionEntity;
import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.tecnificaciones.TecnificacionEntity;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "entrenamientos")
public class EntrenamientoEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", length = 255)
    private String nombre;

    @Column(name = "slug", length = 255, unique = true)
    private String slug;
    
    @Column(name = "descripcion", length = 1000)
    private String descripcion;
    
    @Column(name = "nivel", length = 50)
    private String nivel;
    
    @Column(name = "edad_minima")
    private Integer edadMinima;
    
    @Column(name = "edad_maxima")
    private Integer edadMaxima;
    
    @ManyToOne
    @JoinColumn(name = "tecnificacion_id")
    private TecnificacionEntity tecnificacion;
    
    @ManyToOne
    @JoinColumn(name = "subtipo_tecnificacion_id")
    private SubtipoTecnificacionEntity subtipoTecnificacion;
    
    @Column(name = "entrenador_id")
    private Long entrenadorId;
    
    @OneToOne(mappedBy = "entrenamiento")
    private HorarioPistaEntity horarioPista;
    
    @Column(name = "max_plazas")
    private Integer maxPlazas;
    
    @Column(name = "objetivos", length = 1000)
    private String objetivos;
    
    @Column(name = "equipamiento_necesario", length = 1000)
    private String equipamientoNecesario;
    
    @Column(name = "duracion_minutos")
    private Integer duracionMinutos;
    
    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
    
}
