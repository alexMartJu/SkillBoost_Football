package com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl.tecnificaciones;

import com.skillboostfootball.backend_main_springboot.domain.entities.tecnificaciones.Tecnificacion;
import com.skillboostfootball.backend_main_springboot.domain.repositories.tecnificaciones.TecnificacionRepository;
import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.tecnificaciones.TecnificacionEntity;
import com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl.EntityMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class TecnificacionRepositoryImpl implements TecnificacionRepository {
    private final SpringDataTecnificacionRepository repository;
    private final EntityMapper mapper;
    
    @Override
    public List<Tecnificacion> findAllActive() {
        return repository.findByDeletedAtIsNull()
            .stream()
            .map(mapper::toTecnificacion)
            .collect(Collectors.toList());
    }
    
    @Override
    public Optional<Tecnificacion> findBySlug(String slug) {
        return repository.findBySlugAndDeletedAtIsNull(slug).map(mapper::toTecnificacion);
    }
    
    @Override
    public Tecnificacion save(Tecnificacion tecnificacion) {
        TecnificacionEntity entity = mapper.toTecnificacionEntity(tecnificacion);
        entity = repository.save(entity);
        return mapper.toTecnificacion(entity);
    }
    
    @Override
    public void delete(Tecnificacion tecnificacion) {
        TecnificacionEntity entity = repository.findById(tecnificacion.getId()).orElseThrow();
        entity.setDeletedAt(tecnificacion.getDeletedAt());

        // Establecer deletedAt en todos sus subtipos (cascada)
        if (entity.getSubtipos() != null) {
            entity.getSubtipos().forEach(subtipo -> {
                subtipo.setDeletedAt(tecnificacion.getDeletedAt());
            });
        }
        repository.save(entity);
    }
}
