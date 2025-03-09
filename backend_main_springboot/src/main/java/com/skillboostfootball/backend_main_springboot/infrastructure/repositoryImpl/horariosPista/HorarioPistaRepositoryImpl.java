package com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl.horariosPista;

import com.skillboostfootball.backend_main_springboot.domain.entities.horariosPista.HorarioPista;
import com.skillboostfootball.backend_main_springboot.domain.repositories.horariosPista.HorarioPistaRepository;
import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.horariosPista.HorarioPistaEntity;
import com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl.EntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class HorarioPistaRepositoryImpl implements HorarioPistaRepository {
    
    private final SpringDataHorarioPistaRepository repository;
    private final EntityMapper mapper;
    
    @Override
    public List<HorarioPista> findOcupadosByPistaIdAndFecha(Long pistaId, LocalDate fecha) {
        LocalDateTime startOfDay = fecha.atStartOfDay();
        LocalDateTime endOfDay = fecha.atTime(LocalTime.MAX);
        
        return repository.findOcupadosByPistaIdAndFecha(pistaId, startOfDay, endOfDay)
            .stream()
            .map(mapper::toHorarioPista)
            .collect(Collectors.toList());
    }
    
    @Override
    public List<HorarioPista> findOverlappingHorarios(Long pistaId, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return repository.findOverlappingHorarios(pistaId, fechaInicio, fechaFin)
            .stream()
            .map(mapper::toHorarioPista)
            .collect(Collectors.toList());
    }
    
    @Override
    public HorarioPista save(HorarioPista horarioPista) {
        HorarioPistaEntity entity = mapper.toHorarioPistaEntity(horarioPista);
        entity = repository.save(entity);
        return mapper.toHorarioPista(entity);
    }
    
    @Override
    public Optional<HorarioPista> findById(Long id) {
        return repository.findById(id).map(mapper::toHorarioPista);
    }
    
    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }
    
}
