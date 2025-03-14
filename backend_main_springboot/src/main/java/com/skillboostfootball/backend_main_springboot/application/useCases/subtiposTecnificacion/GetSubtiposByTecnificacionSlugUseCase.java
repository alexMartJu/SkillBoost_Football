package com.skillboostfootball.backend_main_springboot.application.useCases.subtiposTecnificacion;

import com.skillboostfootball.backend_main_springboot.domain.entities.subtiposTecnificacion.SubtipoTecnificacion;
import com.skillboostfootball.backend_main_springboot.domain.entities.tecnificaciones.Tecnificacion;
import com.skillboostfootball.backend_main_springboot.domain.exceptions.tecnificaciones.TecnificacionNotFoundException;
import com.skillboostfootball.backend_main_springboot.domain.repositories.tecnificaciones.TecnificacionRepository;
import com.skillboostfootball.backend_main_springboot.domain.repositories.subtiposTecnificacion.SubtipoTecnificacionRepository;
import com.skillboostfootball.backend_main_springboot.application.services.images.ImageService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetSubtiposByTecnificacionSlugUseCase {
    
    private final TecnificacionRepository tecnificacionRepository;
    private final SubtipoTecnificacionRepository subtipoTecnificacionRepository;
    private final ImageService imageService;
    
    @Transactional(readOnly = true)
    public List<SubtipoTecnificacion> execute(String tecnificacionSlug) {
        
        Tecnificacion tecnificacion = tecnificacionRepository.findBySlug(tecnificacionSlug).orElseThrow(TecnificacionNotFoundException::new);
        
        //Buscar todos los subtipos activos de esta tecnificación
        List<SubtipoTecnificacion> subtipos = subtipoTecnificacionRepository.findAllActive().stream()
            .filter(subtipo -> subtipo.getTecnificacion() != null && subtipo.getTecnificacion().getId().equals(tecnificacion.getId()))
            .collect(Collectors.toList());
        
        //Cargar imágenes para cada subtipo
        subtipos.forEach(subtipo -> {
            var images = imageService.getImagesForEntity(
                "App\\Models\\SubtipoTecnificacion", 
                subtipo.getId()
            );
            subtipo.getImages().addAll(images);
        });
        return subtipos;
        
    }
}
