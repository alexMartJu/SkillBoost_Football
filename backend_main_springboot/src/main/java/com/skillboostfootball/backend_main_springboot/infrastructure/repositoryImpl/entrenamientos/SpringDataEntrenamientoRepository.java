package com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl.entrenamientos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.entrenamientos.EntrenamientoEntity;
import java.util.Optional;
import java.util.List;

public interface SpringDataEntrenamientoRepository extends JpaRepository<EntrenamientoEntity, Long>, JpaSpecificationExecutor<EntrenamientoEntity> {
    
    Optional<EntrenamientoEntity> findBySlugAndDeletedAtIsNull(String slug);
    Long countByDeletedAtIsNullAndStatusEquals(String status);
    List<EntrenamientoEntity> findByDeletedAtIsNullAndStatusEquals(String status);
    List<EntrenamientoEntity> findByStatusEqualsAndDeletedAtIsNull(String status);
    List<EntrenamientoEntity> findByEntrenadorIdAndStatusInAndDeletedAtIsNull(Long entrenadorId, List<String> statusList);

}
