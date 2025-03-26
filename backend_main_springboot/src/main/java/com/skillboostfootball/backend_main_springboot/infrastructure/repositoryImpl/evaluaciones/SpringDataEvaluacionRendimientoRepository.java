package com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl.evaluaciones;

import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.evaluaciones.EvaluacionRendimientoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface SpringDataEvaluacionRendimientoRepository extends JpaRepository<EvaluacionRendimientoEntity, Long> {

    Optional<EvaluacionRendimientoEntity> findByProfile_IdAndEntrenamiento_IdAndDeletedAtIsNull(Long profileId, Long entrenamientoId);
    List<EvaluacionRendimientoEntity> findByProfile_IdAndDeletedAtIsNull(Long profileId);
    List<EvaluacionRendimientoEntity> findByEntrenamiento_IdAndDeletedAtIsNull(Long entrenamientoId);
    
}
