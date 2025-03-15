package com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl.logros;

import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.logros.LogroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataLogroRepository extends JpaRepository<LogroEntity, Long> {

    List<LogroEntity> findByRequisitoEntrenamientos(Integer requisitoEntrenamientos);
    List<LogroEntity> findAllByOrderByRequisitoEntrenamientosAsc();
    
}
