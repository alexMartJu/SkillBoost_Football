package com.skillboostfootball.backend_main_springboot.application.useCases.entrenamientos;

import com.skillboostfootball.backend_main_springboot.domain.repositories.entrenamientos.EntrenamientoRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CountEntrenamientosWithFiltersUseCase {
    
    private final EntrenamientoRepository entrenamientoRepository;
    
    @Transactional(readOnly = true)
    public Long execute(
            String nombre,
            String nivel,
            Integer edadMinima,
            Integer edadMaxima,
            Integer maxPlazasMin,
            Integer maxPlazasMax,
            String tecnificacionNombre,
            LocalDateTime fechaInicio,
            LocalDateTime fechaFin) {
        
        return entrenamientoRepository.countWithFilters(
                nombre, nivel, edadMinima, edadMaxima, maxPlazasMin, maxPlazasMax, tecnificacionNombre, fechaInicio, fechaFin);
    }
}
