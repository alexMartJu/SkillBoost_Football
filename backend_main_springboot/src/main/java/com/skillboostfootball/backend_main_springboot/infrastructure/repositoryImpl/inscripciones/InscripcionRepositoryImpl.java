package com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl.inscripciones;


import com.skillboostfootball.backend_main_springboot.domain.entities.reservas.Reserva;
import com.skillboostfootball.backend_main_springboot.domain.repositories.inscripciones.InscripcionRepository;
import com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl.EntityMapper;
import com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl.reservas.SpringDataReservaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class InscripcionRepositoryImpl implements InscripcionRepository {
    private final SpringDataReservaRepository reservaRepository;
    private final EntityMapper mapper;

    @Override
    public List<Reserva> findByEntrenamientoId(Long entrenamientoId) {
        return reservaRepository.findByEntrenamientoIdAndDeletedAtIsNull(entrenamientoId)
                .stream()
                .map(mapper::toReserva)
                .collect(Collectors.toList());
    }

    @Override
    public List<Reserva> findByEntrenamientoEntrenadorId(Long entrenadorId) {
        return reservaRepository.findByEntrenamientoEntrenadorIdAndDeletedAtIsNull(entrenadorId)
                .stream()
                .map(mapper::toReserva)
                .collect(Collectors.toList());
    }

    @Override
    public List<Reserva> findByEntrenamientoIdAndProfileId(Long entrenamientoId, Long profileId) {
        return reservaRepository.findByEntrenamientoIdAndProfileIdAndDeletedAtIsNull(entrenamientoId, profileId)
                .stream()
                .map(mapper::toReserva)
                .collect(Collectors.toList());
    }

}