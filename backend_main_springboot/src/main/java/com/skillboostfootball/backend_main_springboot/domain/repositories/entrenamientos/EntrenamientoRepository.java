package com.skillboostfootball.backend_main_springboot.domain.repositories.entrenamientos;

import com.skillboostfootball.backend_main_springboot.domain.entities.entrenamientos.Entrenamiento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Optional;

public interface EntrenamientoRepository {
    
    Page<Entrenamiento> findAllWithFilters(
            String nombre,
            String nivel,
            Integer edadMinima,
            Integer edadMaxima,
            Integer maxPlazasMin,
            Integer maxPlazasMax,
            String tecnificacionNombre,
            LocalDateTime fechaInicio,
            LocalDateTime fechaFin,
            Pageable pageable);
    
    Optional<Entrenamiento> findBySlug(String slug);
    Long countActiveEntrenamientos();
    Integer findMinPlazas();
    Integer findMaxPlazas();

}
