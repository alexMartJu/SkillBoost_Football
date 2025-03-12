package com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl.reservas;

import com.skillboostfootball.backend_main_springboot.domain.entities.reservas.Reserva;
import com.skillboostfootball.backend_main_springboot.domain.repositories.reservas.ReservaRepository;
import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.reservas.ReservaEntity;
import com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl.EntityMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ReservaRepositoryImpl implements ReservaRepository {
    private final SpringDataReservaRepository repository;
    private final EntityMapper mapper;

    @Override
    public Reserva save(Reserva reserva) {
        ReservaEntity entity = mapper.toReservaEntity(reserva);
        entity = repository.save(entity);
        return mapper.toReserva(entity);
    }

    @Override
    public Optional<Reserva> findByProfileIdAndEntrenamientoId(Long profileId, Long entrenamientoId) {
        return repository.findByProfileIdAndEntrenamientoIdAndDeletedAtIsNull(profileId, entrenamientoId)
                .map(mapper::toReserva);
    }

    @Override
    public List<Reserva> findByProfileId(Long profileId) {
        return repository.findByProfileIdAndDeletedAtIsNull(profileId)
                .stream()
                .map(mapper::toReserva)
                .collect(Collectors.toList());
    }

    @Override
    public List<Reserva> findByProfileIdAndEntrenamientoStatus(Long profileId, String status) {
        return repository.findByProfileIdAndEntrenamientoStatus(profileId, status)
                .stream()
                .map(mapper::toReserva)
                .collect(Collectors.toList());
    }

    @Override
    public int countByEntrenamientoId(Long entrenamientoId) {
        return repository.countByEntrenamientoId(entrenamientoId);
    }

    @Override
    @Transactional
    public void deleteByProfileIdAndEntrenamientoId(Long profileId, Long entrenamientoId) {
        repository.deleteByProfileIdAndEntrenamientoId(profileId, entrenamientoId);
    }

    @Override
    @Transactional
    public void deleteByEntrenamientoId(Long entrenamientoId) {
        repository.deleteByEntrenamientoId(entrenamientoId);
    }
}
