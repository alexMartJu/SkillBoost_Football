package com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl.incidencias;

import com.skillboostfootball.backend_main_springboot.domain.entities.incidencias.Incidencia;
import com.skillboostfootball.backend_main_springboot.domain.repositories.incidencias.IncidenciaRepository;
import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.incidencias.IncidenciaEntity;
import com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl.EntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class IncidenciaRepositoryImpl implements IncidenciaRepository {
    private final SpringDataIncidenciaRepository repository;
    private final EntityMapper mapper;
    
    @Override
    public Incidencia save(Incidencia incidencia) {
        IncidenciaEntity entity = mapper.toIncidenciaEntity(incidencia);
        entity = repository.save(entity);
        return mapper.toIncidencia(entity);
    }
}
