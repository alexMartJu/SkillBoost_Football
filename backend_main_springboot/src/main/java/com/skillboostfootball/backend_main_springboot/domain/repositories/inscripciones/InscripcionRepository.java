package com.skillboostfootball.backend_main_springboot.domain.repositories.inscripciones;

import com.skillboostfootball.backend_main_springboot.domain.entities.reservas.Reserva;
import java.util.List;

public interface InscripcionRepository {

    List<Reserva> findByEntrenamientoId(Long entrenamientoId);
    List<Reserva> findByEntrenamientoEntrenadorId(Long entrenadorId);
    List<Reserva> findByEntrenamientoIdAndProfileId(Long entrenamientoId, Long profileId);
    
}
