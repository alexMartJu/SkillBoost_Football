package com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl.graficas;

import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.graficas.GraficaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SpringDataGraficaRepository extends JpaRepository<GraficaEntity, Long> {

    List<GraficaEntity> findByProfile_NumeroSocioAndAñoAndDeletedAtIsNull(String numeroSocio, Integer año);

}
