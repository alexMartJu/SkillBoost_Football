package com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl.incidencias;

import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.incidencias.IncidenciaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataIncidenciaRepository extends JpaRepository<IncidenciaEntity, Long> {
}
