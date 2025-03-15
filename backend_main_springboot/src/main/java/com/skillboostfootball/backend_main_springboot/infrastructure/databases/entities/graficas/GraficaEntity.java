package com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.graficas;

import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.profiles.ProfileEntity;
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
@Table(name = "graficas")
public class GraficaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "seccion")
    private String seccion;

    @Column(name = "nivel")
    private Integer nivel;

    @Column(name = "Mes")
    private Integer mes;

    @Column(name = "año")
    private Integer año;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private ProfileEntity profile;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
    
}
