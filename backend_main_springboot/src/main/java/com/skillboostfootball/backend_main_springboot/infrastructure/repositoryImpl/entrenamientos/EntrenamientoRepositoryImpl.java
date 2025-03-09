package com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl.entrenamientos;

import com.skillboostfootball.backend_main_springboot.domain.entities.entrenamientos.Entrenamiento;
import com.skillboostfootball.backend_main_springboot.domain.repositories.entrenamientos.EntrenamientoRepository;
import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.entrenamientos.EntrenamientoEntity;
import com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl.EntityMapper;
import com.skillboostfootball.backend_main_springboot.infrastructure.spec.EntrenamientoSpecification;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class EntrenamientoRepositoryImpl implements EntrenamientoRepository {
    
    private final SpringDataEntrenamientoRepository repository;
    private final EntityMapper mapper;
    
    @Override
    public Page<Entrenamiento> findAllWithFilters(
            String nombre,
            String nivel,
            Integer edadMinima,
            Integer edadMaxima,
            Integer maxPlazasMin,
            Integer maxPlazasMax,
            String tecnificacionNombre,
            LocalDateTime fechaInicio,
            LocalDateTime fechaFin,
            Pageable pageable) {
            
        return repository.findAll(
            EntrenamientoSpecification.withFilters(
                nombre, nivel, edadMinima, edadMaxima,
                maxPlazasMin, maxPlazasMax, tecnificacionNombre,
                fechaInicio, fechaFin
            ), pageable).map(mapper::toEntrenamiento);
    }
    
    @Override
    public Optional<Entrenamiento> findBySlug(String slug) {
        return repository.findBySlugAndDeletedAtIsNull(slug).map(mapper::toEntrenamiento);
    }

    @Override
    public Long countActiveEntrenamientos() {
        return repository.countByDeletedAtIsNullAndStatusEquals("active");
    }

    @Override
    public Integer findMinPlazas() {
        List<EntrenamientoEntity> entrenamientoEntities = repository.findByDeletedAtIsNullAndStatusEquals("active");
        List<Entrenamiento> entrenamientos = entrenamientoEntities.stream().map(mapper::toEntrenamiento).collect(Collectors.toList());
        
        return entrenamientos.stream().filter(e -> e.getMaxPlazas() != null).map(Entrenamiento::getMaxPlazas).min(Integer::compare).orElse(null);
    }

    @Override
    public Integer findMaxPlazas() {
        List<EntrenamientoEntity> entrenamientoEntities = repository.findByDeletedAtIsNullAndStatusEquals("active");
        List<Entrenamiento> entrenamientos = entrenamientoEntities.stream().map(mapper::toEntrenamiento).collect(Collectors.toList());
        
        return entrenamientos.stream()
            .filter(e -> e.getMaxPlazas() != null)
            .map(Entrenamiento::getMaxPlazas)
            .max(Integer::compare)
            .orElse(null);
    }

    @Override
    public Long countWithFilters(
            String nombre,
            String nivel,
            Integer edadMinima,
            Integer edadMaxima,
            Integer maxPlazasMin,
            Integer maxPlazasMax,
            String tecnificacionNombre,
            LocalDateTime fechaInicio,
            LocalDateTime fechaFin) {
            
        return repository.count(
            EntrenamientoSpecification.withFilters(
                nombre, nivel, edadMinima, edadMaxima,
                maxPlazasMin, maxPlazasMax, tecnificacionNombre,
                fechaInicio, fechaFin
            )
        );
    }

    @Override
    public Optional<Entrenamiento> findById(Long id) {
        return repository.findById(id).map(mapper::toEntrenamiento);
    }

    @Override
    public Entrenamiento save(Entrenamiento entrenamiento) {
        EntrenamientoEntity entity = mapper.toEntrenamientoEntity(entrenamiento);
        entity = repository.save(entity);
        return mapper.toEntrenamiento(entity);
    }

    @Override
    public List<Entrenamiento> findByStatus(String status) {
        List<EntrenamientoEntity> entities = repository.findByStatusEqualsAndDeletedAtIsNull(status);
        return entities.stream()
            .map(mapper::toEntrenamiento)
            .collect(Collectors.toList());
    }

    @Override
    public List<Entrenamiento> findByEntrenadorIdAndStatusIn(Long entrenadorId, List<String> statusList) {
        List<EntrenamientoEntity> entities = repository.findByEntrenadorIdAndStatusInAndDeletedAtIsNull(entrenadorId, statusList);
        return entities.stream()
            .map(mapper::toEntrenamiento)
            .collect(Collectors.toList());
    }
    
}
