package com.skillboostfootball.backend_main_springboot.application.useCases.tecnificaciones;

import com.skillboostfootball.backend_main_springboot.domain.exceptions.tecnificaciones.TecnificacionNotFoundException;
import com.skillboostfootball.backend_main_springboot.domain.entities.tecnificaciones.Tecnificacion;
import com.skillboostfootball.backend_main_springboot.domain.repositories.tecnificaciones.TecnificacionRepository;
import com.skillboostfootball.backend_main_springboot.application.services.images.ImageService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetTecnificacionBySlugUseCase {
    private final TecnificacionRepository tecnificacionRepository;
    private final ImageService imageService;
    
    @Transactional(readOnly = true)
    public Tecnificacion execute(String slug) {
        Tecnificacion tecnificacion = tecnificacionRepository.findBySlug(slug).orElseThrow(TecnificacionNotFoundException::new);
        
        // Cargar imágenes para la tecnificación usando el servicio
        var images = imageService.getImagesForEntity(
            "App\\Models\\Tecnificacion", 
            tecnificacion.getId()
        );
        tecnificacion.getImages().addAll(images);
        
        return tecnificacion;
    }
}
