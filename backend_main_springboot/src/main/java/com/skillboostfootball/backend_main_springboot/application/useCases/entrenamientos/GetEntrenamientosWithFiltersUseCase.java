package com.skillboostfootball.backend_main_springboot.application.useCases.entrenamientos;

import com.skillboostfootball.backend_main_springboot.domain.entities.entrenamientos.Entrenamiento;
import com.skillboostfootball.backend_main_springboot.domain.repositories.entrenamientos.EntrenamientoRepository;
import com.skillboostfootball.backend_main_springboot.application.services.images.ImageService;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetEntrenamientosWithFiltersUseCase {
    
    private final EntrenamientoRepository entrenamientoRepository;
    private final ImageService imageService;
    
    @Transactional(readOnly = true)
    public Page<Entrenamiento> execute(
            String nombre,
            String nivel,
            Integer edadMinima,
            Integer edadMaxima,
            Integer maxPlazasMin,
            Integer maxPlazasMax,
            String tecnificacionNombre,
            LocalDateTime fechaInicio,
            LocalDateTime fechaFin,
            Pageable pageable) {
        
        Page<Entrenamiento> entrenamientos = entrenamientoRepository.findAllWithFilters(
                nombre, nivel, edadMinima, edadMaxima, maxPlazasMin, maxPlazasMax, tecnificacionNombre, fechaInicio, fechaFin, pageable);
        
        entrenamientos.forEach(entrenamiento -> {
            var images = imageService.getImagesForEntity("Entrenamiento", entrenamiento.getId());
            entrenamiento.getImages().addAll(images);
        });
        
        return entrenamientos;
    }
    
}
