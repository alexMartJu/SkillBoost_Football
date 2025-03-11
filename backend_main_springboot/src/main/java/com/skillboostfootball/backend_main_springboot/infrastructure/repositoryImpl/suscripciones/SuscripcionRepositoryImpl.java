package com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl.suscripciones;

import com.skillboostfootball.backend_main_springboot.domain.entities.suscripciones.Suscripcion;
import com.skillboostfootball.backend_main_springboot.domain.repositories.suscripciones.SuscripcionRepository;
import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.suscripciones.SuscripcionEntity;
import com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl.EntityMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class SuscripcionRepositoryImpl implements SuscripcionRepository {

    private final SpringDataSuscripcionRepository repository;
    private final EntityMapper mapper;
    
    @Override
    public List<Suscripcion> findAll() {
        return repository.findAll()
            .stream()
            .map(mapper::toSuscripcion)
            .collect(Collectors.toList());
    }
    
    @Override
    public Optional<Suscripcion> findBySlug(String slug) {
        return repository.findBySlug(slug).map(mapper::toSuscripcion);
    }
    
    @Override
    public Suscripcion save(Suscripcion suscripcion) {
        SuscripcionEntity entity = mapper.toSuscripcionEntity(suscripcion);
        entity = repository.save(entity);
        return mapper.toSuscripcion(entity);
    }

    @Override
    public Optional<Suscripcion> findById(Long id) {
        return repository.findById(id).map(mapper::toSuscripcion);
    }

}
