package com.skillboostfootball.backend_main_springboot.application.useCases.entrenamientos;

import com.skillboostfootball.backend_main_springboot.domain.entities.entrenamientos.Entrenamiento;
import com.skillboostfootball.backend_main_springboot.domain.exceptions.entrenamientos.EntrenamientoNotFoundException;
import com.skillboostfootball.backend_main_springboot.domain.repositories.entrenamientos.EntrenamientoRepository;
import com.skillboostfootball.backend_main_springboot.application.services.images.ImageService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetEntrenamientoBySlugUseCase {
    
    private final EntrenamientoRepository entrenamientoRepository;
    private final ImageService imageService;
    
    @Transactional(readOnly = true)
    public Entrenamiento execute(String slug) {
        Entrenamiento entrenamiento = entrenamientoRepository.findBySlug(slug).orElseThrow(EntrenamientoNotFoundException::new);
        
        var images = imageService.getImagesForEntity("Entrenamiento", entrenamiento.getId());
        entrenamiento.getImages().addAll(images);
        
        return entrenamiento;
    }
}
