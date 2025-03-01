package com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl.subtiposTecnificacion;

import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.subtiposTecnificacion.SubtipoTecnificacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SpringDataSubtipoTecnificacionRepository extends JpaRepository<SubtipoTecnificacionEntity, Long> {
    List<SubtipoTecnificacionEntity> findByDeletedAtIsNull();
    Optional<SubtipoTecnificacionEntity> findBySlugAndDeletedAtIsNull(String slug);
}
