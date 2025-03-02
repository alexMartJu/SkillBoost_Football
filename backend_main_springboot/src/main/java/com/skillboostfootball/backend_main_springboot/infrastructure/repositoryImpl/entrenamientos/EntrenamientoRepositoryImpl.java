package com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl.entrenamientos;

import com.skillboostfootball.backend_main_springboot.domain.entities.entrenamientos.Entrenamiento;
import com.skillboostfootball.backend_main_springboot.domain.repositories.entrenamientos.EntrenamientoRepository;
import com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl.EntityMapper;
import com.skillboostfootball.backend_main_springboot.infrastructure.spec.EntrenamientoSpecification;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

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
    
}
