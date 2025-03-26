package com.skillboostfootball.backend_main_springboot.domain.repositories.evaluaciones;

import com.skillboostfootball.backend_main_springboot.domain.entities.evaluaciones.EvaluacionRendimiento;
import java.util.List;
import java.util.Optional;

public interface EvaluacionRendimientoRepository {

    EvaluacionRendimiento save(EvaluacionRendimiento evaluacion);
    Optional<EvaluacionRendimiento> findByProfileIdAndEntrenamientoId(Long profileId, Long entrenamientoId);
    List<EvaluacionRendimiento> findByProfileId(Long profileId);
    List<EvaluacionRendimiento> findByEntrenamientoId(Long entrenamientoId);
    
}
