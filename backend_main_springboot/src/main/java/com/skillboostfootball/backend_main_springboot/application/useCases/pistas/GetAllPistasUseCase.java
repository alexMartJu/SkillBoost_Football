package com.skillboostfootball.backend_main_springboot.application.useCases.pistas;

import com.skillboostfootball.backend_main_springboot.domain.exceptions.pistas.PistaNotFoundException;
import com.skillboostfootball.backend_main_springboot.domain.entities.pistas.Pista;
import com.skillboostfootball.backend_main_springboot.domain.repositories.pistas.PistaRepository;
import com.skillboostfootball.backend_main_springboot.application.services.images.ImageService;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllPistasUseCase {
    
    private final PistaRepository pistaRepository;
    private final ImageService imageService;
    
    @Transactional(readOnly = true)
    public List<Pista> execute() {

        List<Pista> pistas = pistaRepository.findAllActive();
        
        if (pistas.isEmpty()) {
            throw new PistaNotFoundException();
        }
        
        pistas.forEach(pista -> {
            var images = imageService.getImagesForEntity(
                "Pista", 
                pista.getId()
            );
            pista.getImages().addAll(images);
        });
        
        return pistas;
    }

}
