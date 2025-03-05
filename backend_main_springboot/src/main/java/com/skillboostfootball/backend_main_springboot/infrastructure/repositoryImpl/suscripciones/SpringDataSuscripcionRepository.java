package com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl.suscripciones;

import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.suscripciones.SuscripcionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SpringDataSuscripcionRepository extends JpaRepository<SuscripcionEntity, Long> {

    Optional<SuscripcionEntity> findBySlug(String slug);
    
}
