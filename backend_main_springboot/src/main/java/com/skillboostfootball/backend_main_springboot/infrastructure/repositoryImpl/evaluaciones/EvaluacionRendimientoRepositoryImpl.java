package com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl.evaluaciones;


import com.skillboostfootball.backend_main_springboot.domain.entities.evaluaciones.EvaluacionRendimiento;
import com.skillboostfootball.backend_main_springboot.domain.repositories.evaluaciones.EvaluacionRendimientoRepository;
import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.evaluaciones.EvaluacionRendimientoEntity;
import com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl.EntityMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class EvaluacionRendimientoRepositoryImpl implements EvaluacionRendimientoRepository {

    private final SpringDataEvaluacionRendimientoRepository repository;
    private final EntityMapper mapper;

    @Override
    public EvaluacionRendimiento save(EvaluacionRendimiento evaluacion) {
        EvaluacionRendimientoEntity entity = mapper.toEvaluacionRendimientoEntity(evaluacion);
        entity = repository.save(entity);
        return mapper.toEvaluacionRendimiento(entity);
    }

    @Override
    public Optional<EvaluacionRendimiento> findByProfileIdAndEntrenamientoId(Long profileId, Long entrenamientoId) {
        return repository.findByProfile_IdAndEntrenamiento_IdAndDeletedAtIsNull(profileId, entrenamientoId)
                .map(mapper::toEvaluacionRendimiento);
    }

    @Override
    public List<EvaluacionRendimiento> findByProfileId(Long profileId) {
        return repository.findByProfile_IdAndDeletedAtIsNull(profileId)
                .stream()
                .map(mapper::toEvaluacionRendimiento)
                .collect(Collectors.toList());
    }

    @Override
    public List<EvaluacionRendimiento> findByEntrenamientoId(Long entrenamientoId) {
        return repository.findByEntrenamiento_IdAndDeletedAtIsNull(entrenamientoId)
                .stream()
                .map(mapper::toEvaluacionRendimiento)
                .collect(Collectors.toList());
    }

}
