package com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl.pistas;

import com.skillboostfootball.backend_main_springboot.domain.entities.pistas.Pista;
import com.skillboostfootball.backend_main_springboot.domain.repositories.pistas.PistaRepository;
import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.pistas.PistaEntity;
import com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl.EntityMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class PistaRepositoryImpl implements PistaRepository {
    
    private final SpringDataPistaRepository repository;
    private final EntityMapper mapper;
    
    @Override
    public List<Pista> findAllActive() {
        return repository.findByDeletedAtIsNull()
            .stream()
            .map(mapper::toPista)
            .collect(Collectors.toList());
    }
    
    @Override
    public Optional<Pista> findBySlug(String slug) {
        return repository.findBySlugAndDeletedAtIsNull(slug).map(mapper::toPista);
    }
    
    @Override
    public Pista save(Pista pista) {
        PistaEntity entity = mapper.toPistaEntity(pista);
        entity = repository.save(entity);
        return mapper.toPista(entity);
    }
    
    @Override
    public void delete(Pista pista) {
        PistaEntity entity = repository.findById(pista.getId()).orElseThrow();
        entity.setDeletedAt(pista.getDeletedAt());
        repository.save(entity);
    }

}
