package com.skillboostfootball.backend_main_springboot.application.useCases.subtiposTecnificacion;

import com.skillboostfootball.backend_main_springboot.domain.exceptions.subtiposTecnificacion.SubtipoTecnificacionNotFoundException;
import com.skillboostfootball.backend_main_springboot.domain.entities.subtiposTecnificacion.SubtipoTecnificacion;
import com.skillboostfootball.backend_main_springboot.domain.repositories.subtiposTecnificacion.SubtipoTecnificacionRepository;
import com.skillboostfootball.backend_main_springboot.application.services.images.ImageService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllSubtipoTecnificacionesScrollUseCase {
    
    private final SubtipoTecnificacionRepository subtipoTecnificacionRepository;
    private final ImageService imageService;
    
    @Transactional(readOnly = true)
    public List<SubtipoTecnificacion> execute(Pageable pageable) {
        
        Page<SubtipoTecnificacion> subtiposPage = subtipoTecnificacionRepository.findAllActive(pageable);
        if (subtiposPage.isEmpty()) {
            throw new SubtipoTecnificacionNotFoundException();
        }
        
        List<SubtipoTecnificacion> subtipos = subtiposPage.getContent();
        subtipos.forEach(subtipo -> {
            var images = imageService.getImagesForEntity("App\\Models\\SubtipoTecnificacion", subtipo.getId());
            subtipo.getImages().addAll(images);
        });
        
        return subtipos;
    }
}
