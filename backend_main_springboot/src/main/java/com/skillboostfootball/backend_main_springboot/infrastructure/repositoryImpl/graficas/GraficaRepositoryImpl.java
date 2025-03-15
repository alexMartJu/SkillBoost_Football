package com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl.graficas;

import com.skillboostfootball.backend_main_springboot.domain.entities.graficas.Grafica;
import com.skillboostfootball.backend_main_springboot.domain.repositories.graficas.GraficaRepository;
import com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl.EntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class GraficaRepositoryImpl implements GraficaRepository {

    private final SpringDataGraficaRepository repository;
    private final EntityMapper mapper;

    @Override
    public List<Grafica> findByProfileNumeroSocioAndAño(String numeroSocio, Integer año) {
        return repository.findByProfile_NumeroSocioAndAñoAndDeletedAtIsNull(numeroSocio, año)
                .stream()
                .map(mapper::toGrafica)
                .collect(Collectors.toList());
    }

}
