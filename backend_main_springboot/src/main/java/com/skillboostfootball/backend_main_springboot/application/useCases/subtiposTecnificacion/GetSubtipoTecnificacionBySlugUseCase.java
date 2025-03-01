package com.skillboostfootball.backend_main_springboot.application.useCases.subtiposTecnificacion;

import com.skillboostfootball.backend_main_springboot.domain.exceptions.subtiposTecnificacion.SubtipoTecnificacionNotFoundException;
import com.skillboostfootball.backend_main_springboot.domain.entities.subtiposTecnificacion.SubtipoTecnificacion;
import com.skillboostfootball.backend_main_springboot.domain.repositories.subtiposTecnificacion.SubtipoTecnificacionRepository;
import com.skillboostfootball.backend_main_springboot.application.services.images.ImageService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetSubtipoTecnificacionBySlugUseCase {
    
    private final SubtipoTecnificacionRepository subtipoTecnificacionRepository;
    private final ImageService imageService;  // Cambio aquí
    
    @Transactional(readOnly = true)
    public SubtipoTecnificacion execute(String slug) {
        SubtipoTecnificacion subtipo = subtipoTecnificacionRepository.findBySlug(slug).orElseThrow(SubtipoTecnificacionNotFoundException::new);
        
        var images = imageService.getImagesForEntity(
            "App\\Models\\SubtipoTecnificacion", 
            subtipo.getId()
        );
        subtipo.getImages().addAll(images);
        
        return subtipo;
    }
}
