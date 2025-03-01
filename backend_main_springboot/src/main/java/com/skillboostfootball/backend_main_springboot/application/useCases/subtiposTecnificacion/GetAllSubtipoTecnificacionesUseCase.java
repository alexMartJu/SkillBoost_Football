package com.skillboostfootball.backend_main_springboot.application.useCases.subtiposTecnificacion;

import com.skillboostfootball.backend_main_springboot.domain.exceptions.subtiposTecnificacion.SubtipoTecnificacionNotFoundException;
import com.skillboostfootball.backend_main_springboot.domain.entities.subtiposTecnificacion.SubtipoTecnificacion;
import com.skillboostfootball.backend_main_springboot.domain.repositories.subtiposTecnificacion.SubtipoTecnificacionRepository;
import com.skillboostfootball.backend_main_springboot.application.services.images.ImageService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllSubtipoTecnificacionesUseCase {
    
    private final SubtipoTecnificacionRepository subtipoTecnificacionRepository;
    private final ImageService imageService;
    
    @Transactional(readOnly = true)
    public List<SubtipoTecnificacion> execute() {
        List<SubtipoTecnificacion> subtipos = subtipoTecnificacionRepository.findAllActive();
        
        if (subtipos.isEmpty()) {
            throw new SubtipoTecnificacionNotFoundException();
        }
        
        //Cargar imágenes para cada subtipo usando el servicio
        subtipos.forEach(subtipo -> {
            var images = imageService.getImagesForEntity(
                "SubtipoTecnificacion", 
                subtipo.getId()
            );
            subtipo.getImages().addAll(images);
        });
        
        return subtipos;
    }
}
