package com.skillboostfootball.backend_main_springboot.domain.entities.reservas;

import com.skillboostfootball.backend_main_springboot.domain.entities.entrenamientos.Entrenamiento;
import com.skillboostfootball.backend_main_springboot.domain.entities.profiles.Profile;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class Reserva {
    private Long id;
    private Profile profile;
    private Entrenamiento entrenamiento;
    private LocalDateTime fecha;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    
    //Métodos de dominio
    public void cancel() {
        this.deletedAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    
    public boolean isCancelled() {
        return this.deletedAt != null;
    }
}
