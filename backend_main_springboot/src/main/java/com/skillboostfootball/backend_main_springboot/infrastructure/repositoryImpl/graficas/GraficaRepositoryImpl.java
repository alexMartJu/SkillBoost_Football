package com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl.graficas;

import com.skillboostfootball.backend_main_springboot.domain.entities.graficas.Grafica;
import com.skillboostfootball.backend_main_springboot.domain.repositories.graficas.GraficaRepository;
import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.graficas.GraficaEntity;
import com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl.EntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

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

    @Override
    public Optional<Grafica> findByProfileIdAndSeccionAndMesAndAño(Long profileId, String seccion, Integer mes, Integer año) {
        return repository.findByProfile_IdAndSeccionAndMesAndAñoAndDeletedAtIsNull(profileId, seccion, mes, año)
                .map(mapper::toGrafica);
    }

    @Override
    public Grafica save(Grafica grafica) {
        GraficaEntity entity = mapper.toGraficaEntity(grafica);
        entity = repository.save(entity);
        return mapper.toGrafica(entity);
    }

}
