package com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl.subtiposTecnificacion;

import com.skillboostfootball.backend_main_springboot.domain.entities.subtiposTecnificacion.SubtipoTecnificacion;
import com.skillboostfootball.backend_main_springboot.domain.repositories.subtiposTecnificacion.SubtipoTecnificacionRepository;
import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.subtiposTecnificacion.SubtipoTecnificacionEntity;
import com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl.EntityMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class SubtipoTecnificacionRepositoryImpl implements SubtipoTecnificacionRepository {
    
    private final SpringDataSubtipoTecnificacionRepository repository;
    private final EntityMapper mapper;
    
    @Override
    public List<SubtipoTecnificacion> findAllActive() {
        return repository.findByDeletedAtIsNull()
            .stream()
            .map(mapper::toSubtipoTecnificacion)
            .collect(Collectors.toList());
    }
    
    @Override
    public Optional<SubtipoTecnificacion> findBySlug(String slug) {
        return repository.findBySlugAndDeletedAtIsNull(slug).map(mapper::toSubtipoTecnificacion);
    }
    
    @Override
    public SubtipoTecnificacion save(SubtipoTecnificacion subtipoTecnificacion) {
        SubtipoTecnificacionEntity entity = mapper.toSubtipoTecnificacionEntity(subtipoTecnificacion);
        entity = repository.save(entity);
        return mapper.toSubtipoTecnificacion(entity);
    }
    
    @Override
    public void delete(SubtipoTecnificacion subtipoTecnificacion) {
        SubtipoTecnificacionEntity entity = repository.findById(subtipoTecnificacion.getId()).orElseThrow();
        entity.setDeletedAt(subtipoTecnificacion.getDeletedAt());
        repository.save(entity);
    }

}
