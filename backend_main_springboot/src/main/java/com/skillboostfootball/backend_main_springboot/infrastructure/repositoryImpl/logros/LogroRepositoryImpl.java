package com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl.logros;

import com.skillboostfootball.backend_main_springboot.domain.entities.logros.Logro;
import com.skillboostfootball.backend_main_springboot.domain.repositories.logros.LogroRepository;
import com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl.EntityMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class LogroRepositoryImpl implements LogroRepository {
    private final SpringDataLogroRepository repository;
    private final EntityMapper mapper;
    
    @Override
    public List<Logro> findAll() {
        return repository.findAll()
            .stream()
            .map(mapper::toLogro)
            .collect(Collectors.toList());
    }
    
    @Override
    public Optional<Logro> findById(Long id) {
        return repository.findById(id).map(mapper::toLogro);
    }
    
    @Override
    public List<Logro> findByRequisitoEntrenamientos(Integer requisitoEntrenamientos) {
        return repository.findByRequisitoEntrenamientos(requisitoEntrenamientos)
            .stream()
            .map(mapper::toLogro)
            .collect(Collectors.toList());
    }
    
    @Override
    public List<Logro> findLogrosOrdenadosPorRequisito() {
        return repository.findAllByOrderByRequisitoEntrenamientosAsc()
            .stream()
            .map(mapper::toLogro)
            .collect(Collectors.toList());
    }
}


