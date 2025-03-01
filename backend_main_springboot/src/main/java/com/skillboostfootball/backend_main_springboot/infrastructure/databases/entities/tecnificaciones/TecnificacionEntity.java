package com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.tecnificaciones;

import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.subtiposTecnificacion.SubtipoTecnificacionEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tecnificaciones")
public class TecnificacionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", length = 191, nullable = false)
    private String nombre;

    @Column(name = "slug", length = 191, unique = true, nullable = false)
    private String slug;
    
    @Column(name = "descripcion", length = 1000)
    private String descripcion;

    @OneToMany(mappedBy = "tecnificacion", cascade = CascadeType.ALL)
    private List<SubtipoTecnificacionEntity> subtipos;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
}
