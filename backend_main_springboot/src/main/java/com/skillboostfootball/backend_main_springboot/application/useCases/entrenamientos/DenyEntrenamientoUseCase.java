package com.skillboostfootball.backend_main_springboot.application.useCases.entrenamientos;

import com.skillboostfootball.backend_main_springboot.domain.entities.entrenamientos.Entrenamiento;
import com.skillboostfootball.backend_main_springboot.domain.exceptions.entrenamientos.EntrenamientoNotFoundException;
import com.skillboostfootball.backend_main_springboot.domain.exceptions.entrenamientos.EntrenamientoInvalidStateException;
import com.skillboostfootball.backend_main_springboot.domain.repositories.entrenamientos.EntrenamientoRepository;
import com.skillboostfootball.backend_main_springboot.application.services.images.ImageService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DenyEntrenamientoUseCase {
    
    private final EntrenamientoRepository entrenamientoRepository;
    private final ImageService imageService;
    
    @Transactional
    public Entrenamiento execute(String slug) {

        Entrenamiento entrenamiento = entrenamientoRepository.findBySlug(slug).orElseThrow(EntrenamientoNotFoundException::new);
        if (!"pending".equals(entrenamiento.getStatus())) {
            throw new EntrenamientoInvalidStateException("Solo se pueden denegar entrenamientos en estado pendiente");
        }
        entrenamiento.updateStatus("denied");
        
        Entrenamiento savedEntrenamiento = entrenamientoRepository.save(entrenamiento);
        var images = imageService.getImagesForEntity("Entrenamiento", savedEntrenamiento.getId());
        savedEntrenamiento.getImages().addAll(images);
        
        return savedEntrenamiento;

    }

}
