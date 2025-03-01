package com.skillboostfootball.backend_main_springboot.application.useCases.pistas;

import com.skillboostfootball.backend_main_springboot.domain.exceptions.pistas.PistaNotFoundException;
import com.skillboostfootball.backend_main_springboot.domain.entities.pistas.Pista;
import com.skillboostfootball.backend_main_springboot.domain.repositories.pistas.PistaRepository;
import com.skillboostfootball.backend_main_springboot.application.services.images.ImageService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetPistaBySlugUseCase {
    
    private final PistaRepository pistaRepository;
    private final ImageService imageService;
    
    @Transactional(readOnly = true)
    public Pista execute(String slug) {
        Pista pista = pistaRepository.findBySlug(slug).orElseThrow(PistaNotFoundException::new);
        
        var images = imageService.getImagesForEntity(
            "Pista", 
            pista.getId()
        );
        pista.getImages().addAll(images);
        
        return pista;
    }
}
