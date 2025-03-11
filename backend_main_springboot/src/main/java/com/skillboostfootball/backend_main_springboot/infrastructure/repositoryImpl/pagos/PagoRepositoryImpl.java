package com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl.pagos;

import com.skillboostfootball.backend_main_springboot.domain.entities.pagos.Pago;
import com.skillboostfootball.backend_main_springboot.domain.repositories.pagos.PagoRepository;
import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.pagos.PagoEntity;
import com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl.EntityMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class PagoRepositoryImpl implements PagoRepository {
    
    private final SpringDataPagoRepository repository;
    private final EntityMapper mapper;
    
    @Override
    public Pago save(Pago pago) {
        PagoEntity entity = mapper.toPagoEntity(pago);
        entity = repository.save(entity);
        return mapper.toPago(entity);
    }
    
    @Override
    public Optional<Pago> findById(Long id) {
        return repository.findById(id).map(mapper::toPago);
    }
    
    @Override
    public List<Pago> findByProfileSuscripcionId(Long profileSuscripcionId) {
        return repository.findByProfileSuscripcionId(profileSuscripcionId)
                .stream()
                .map(mapper::toPago)
                .collect(Collectors.toList());
    }
    
}
