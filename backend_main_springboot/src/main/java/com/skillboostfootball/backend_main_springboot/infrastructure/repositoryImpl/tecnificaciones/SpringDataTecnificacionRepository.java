package com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl.tecnificaciones;

import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.tecnificaciones.TecnificacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
public interface SpringDataTecnificacionRepository extends JpaRepository<TecnificacionEntity, Long> {
    List<TecnificacionEntity> findByDeletedAtIsNull();
    Optional<TecnificacionEntity> findBySlugAndDeletedAtIsNull(String slug);
}
