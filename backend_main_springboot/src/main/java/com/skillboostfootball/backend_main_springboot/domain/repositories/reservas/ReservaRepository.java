package com.skillboostfootball.backend_main_springboot.domain.repositories.reservas;

import com.skillboostfootball.backend_main_springboot.domain.entities.reservas.Reserva;
import java.util.List;
import java.util.Optional;

public interface ReservaRepository {
    Reserva save(Reserva reserva);
    Optional<Reserva> findByProfileIdAndEntrenamientoId(Long profileId, Long entrenamientoId);
    List<Reserva> findByProfileId(Long profileId);
    List<Reserva> findByProfileIdAndEntrenamientoStatus(Long profileId, String status);
    int countByEntrenamientoId(Long entrenamientoId);
    void deleteByProfileIdAndEntrenamientoId(Long profileId, Long entrenamientoId);
    void deleteByEntrenamientoId(Long entrenamientoId);
    int countByProfileId(Long profileId);
}
