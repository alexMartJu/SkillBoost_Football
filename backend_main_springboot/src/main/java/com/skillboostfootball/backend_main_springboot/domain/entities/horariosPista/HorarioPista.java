package com.skillboostfootball.backend_main_springboot.domain.entities.horariosPista;

import com.skillboostfootball.backend_main_springboot.domain.entities.pistas.Pista;
import com.skillboostfootball.backend_main_springboot.domain.entities.entrenamientos.Entrenamiento;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@Builder
public class HorarioPista {
    private Long id;
    private Pista pista;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private Entrenamiento entrenamiento;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    
    //Métodos de dominio
    public void asignarEntrenamiento(Entrenamiento entrenamiento) {
        this.entrenamiento = entrenamiento;
        this.updatedAt = LocalDateTime.now();
    }
    
    public void liberarHorario() {
        this.entrenamiento = null;
        this.updatedAt = LocalDateTime.now();
    }
    
    public void actualizar(LocalDateTime fechaInicio, LocalDateTime fechaFin, Boolean disponible) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.updatedAt = LocalDateTime.now();
    }
    
    public void delete() {
        this.deletedAt = LocalDateTime.now();
    }
    
    public boolean isDeleted() {
        return this.deletedAt != null;
    }
}