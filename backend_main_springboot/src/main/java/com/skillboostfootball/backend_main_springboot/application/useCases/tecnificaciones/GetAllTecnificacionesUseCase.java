package com.skillboostfootball.backend_main_springboot.application.useCases.tecnificaciones;

import com.skillboostfootball.backend_main_springboot.domain.exceptions.tecnificaciones.TecnificacionNotFoundException;
import com.skillboostfootball.backend_main_springboot.domain.entities.tecnificaciones.Tecnificacion;
import com.skillboostfootball.backend_main_springboot.domain.repositories.tecnificaciones.TecnificacionRepository;
import com.skillboostfootball.backend_main_springboot.application.services.images.ImageService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllTecnificacionesUseCase {
    private final TecnificacionRepository tecnificacionRepository;
    private final ImageService imageService;
    
    @Transactional(readOnly = true)
    @Cacheable(value = "tecnificaciones", key = "'all'")
    public List<Tecnificacion> execute() {
        List<Tecnificacion> tecnificaciones = tecnificacionRepository.findAllActive();
        
        if (tecnificaciones.isEmpty()) {
            throw new TecnificacionNotFoundException();
        }
        
        // Cargar imágenes para cada tecnificación
        tecnificaciones.forEach(tecnificacion -> {
            var images = imageService.getImagesForEntity(
                "Tecnificacion", 
                tecnificacion.getId()
            );
            tecnificacion.getImages().addAll(images);
        });
        
        return tecnificaciones;
    }
}
