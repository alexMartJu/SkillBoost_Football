package com.skillboostfootball.backend_main_springboot.domain.entities.profiles;

import com.skillboostfootball.backend_main_springboot.domain.entities.suscripciones.Suscripcion;
import com.skillboostfootball.backend_main_springboot.domain.entities.usuarios.Usuario;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class Profile {
    private Long id;
    private String numeroSocio;
    private String nombre;
    private String apellidos;
    private String bio;
    private String image;
    private Integer edad;
    private String posicionPreferida;
    private String clubOrigen;
    private Boolean esMenor;
    private String numeroEntrenador;
    private String especialidad;
    private Integer experienciaAnios;
    private String certificaciones;
    private String organizacionOrigen;
    private Usuario usuario;
    private Suscripcion suscripcion;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    //Metodos de dominio
    public void update(String nombre, String apellidos, String bio, Integer edad) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.bio = bio;
        this.edad = edad;
        this.updatedAt = LocalDateTime.now();
    }
    
    public void updateCoachInfo(String especialidad, Integer experienciaAnios, String certificaciones) {
        this.especialidad = especialidad;
        this.experienciaAnios = experienciaAnios;
        this.certificaciones = certificaciones;
        this.updatedAt = LocalDateTime.now();
    }
    
    public void updatePlayerInfo(String posicionPreferida, String clubOrigen) {
        this.posicionPreferida = posicionPreferida;
        this.clubOrigen = clubOrigen;
        this.updatedAt = LocalDateTime.now();
    }
    
    public void updateImage(String image) {
        this.image = image;
        this.updatedAt = LocalDateTime.now();
    }
    
    public void setSuscripcion(Suscripcion suscripcion) {
        this.suscripcion = suscripcion;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public boolean isCoach() {
        return numeroEntrenador != null && !numeroEntrenador.isEmpty();
    }
    
    public boolean isFromClub() {
        return clubOrigen != null && !clubOrigen.isEmpty();
    }
    
    public boolean isSocialHelp() {
        return organizacionOrigen != null && !organizacionOrigen.isEmpty();
    }
}
