package com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl.organizaciones;

import com.skillboostfootball.backend_main_springboot.domain.entities.organizaciones.Organizacion;
import com.skillboostfootball.backend_main_springboot.domain.repositories.organizaciones.OrganizacionRepository;
import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.organizaciones.OrganizacionEntity;
import com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl.EntityMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class OrganizacionRepositoryImpl implements OrganizacionRepository {

    private final SpringDataOrganizacionRepository repository;
    private final EntityMapper mapper;
    
    @Override
    public List<Organizacion> findAllActive() {
        return repository.findByDeletedAtIsNull().stream().map(mapper::toOrganizacion).collect(Collectors.toList());
    }
    
    @Override
    public Optional<Organizacion> findBySlug(String slug) {
        return repository.findBySlugAndDeletedAtIsNull(slug).map(mapper::toOrganizacion);
    }
    
    @Override
    public Organizacion save(Organizacion organizacion) {
        OrganizacionEntity entity = mapper.toOrganizacionEntity(organizacion);
        entity = repository.save(entity);
        return mapper.toOrganizacion(entity);
    }
    
    @Override
    public void delete(Organizacion organizacion) {
        OrganizacionEntity entity = repository.findById(organizacion.getId()).orElseThrow();
        entity.setDeletedAt(organizacion.getDeletedAt());
        repository.save(entity);
    }

    @Override
    public boolean existsByNombre(String nombre) {
        return repository.existsByNombreAndDeletedAtIsNull(nombre);
    }

}
