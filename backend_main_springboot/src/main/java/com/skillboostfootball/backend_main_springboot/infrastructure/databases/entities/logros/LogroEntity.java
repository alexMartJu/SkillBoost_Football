package com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.logros;


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
@Table(name = "logros")
public class LogroEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", length = 255, nullable = false, unique = true)
    private String nombre;

    @Column(name = "slug", length = 255)
    private String slug;
    
    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;
    
    @Column(name = "requisito_entrenamientos")
    private Integer requisitoEntrenamientos;
    
    @Column(name = "nivel_dificultad", length = 50)
    private String nivelDificultad;
    
    @Column(name = "recompensa", length = 255)
    private String recompensa;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
}
