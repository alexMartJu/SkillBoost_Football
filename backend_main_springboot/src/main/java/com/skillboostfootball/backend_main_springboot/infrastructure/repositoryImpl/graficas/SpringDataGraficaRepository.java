package com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl.graficas;

import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.graficas.GraficaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface SpringDataGraficaRepository extends JpaRepository<GraficaEntity, Long> {

    List<GraficaEntity> findByProfile_NumeroSocioAndAñoAndDeletedAtIsNull(String numeroSocio, Integer año);
    Optional<GraficaEntity> findByProfile_IdAndSeccionAndMesAndAñoAndDeletedAtIsNull(Long profileId, String seccion, Integer mes, Integer año);

}
