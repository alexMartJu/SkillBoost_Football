package com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.profiles;

import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.suscripciones.SuscripcionEntity;
import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.usuarios.UsuarioEntity;

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
@Table(name = "profiles")
public class ProfileEntity {
    @Id
    private Long id;

    @MapsId 
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private UsuarioEntity usuario;
    
    @ManyToOne
    @JoinColumn(name = "suscripcion_id")
    private SuscripcionEntity suscripcion;

    @Column(name = "numerosocio", unique = true, nullable = false)
    private String numeroSocio;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "apellidos", nullable = false)
    private String apellidos;

    @Column(name = "bio")
    private String bio;

    @Column(name = "image", nullable = false)
    private String image;

    @Column(name = "edad")
    private Integer edad;
    
    @Column(name = "posicion_preferida")
    private String posicionPreferida;
    
    @Column(name = "club_origen")
    private String clubOrigen;
    
    @Column(name = "es_menor", columnDefinition = "boolean default false")
    private Boolean esMenor;
    
    @Column(name = "numeroentrenador")
    private String numeroEntrenador;
    
    @Column(name = "especialidad")
    private String especialidad;
    
    @Column(name = "experiencia_años")
    private Integer experienciaAnios;
    
    @Column(name = "certificaciones")
    private String certificaciones;
    
    @Column(name = "organizacion_origen")
    private String organizacionOrigen;

    @Column(name = "entrenamientos_disponibles", columnDefinition = "INT DEFAULT 0")
    private Integer entrenamientosDisponibles;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    //Método helper para mantener la relación bidireccional
    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
        //Si se establece un usuario, usamos su ID
        if (usuario != null) {
            this.id = usuario.getId();
        }
    }
}
