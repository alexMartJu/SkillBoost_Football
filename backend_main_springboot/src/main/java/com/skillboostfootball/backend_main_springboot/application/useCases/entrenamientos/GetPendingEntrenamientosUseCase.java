package com.skillboostfootball.backend_main_springboot.application.useCases.entrenamientos;

import com.skillboostfootball.backend_main_springboot.domain.entities.entrenamientos.Entrenamiento;
import com.skillboostfootball.backend_main_springboot.domain.repositories.entrenamientos.EntrenamientoRepository;
import com.skillboostfootball.backend_main_springboot.application.services.images.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetPendingEntrenamientosUseCase {
    
    private final EntrenamientoRepository entrenamientoRepository;
    private final ImageService imageService;
    
    @Transactional(readOnly = true)
    public List<Entrenamiento> execute() {

        List<Entrenamiento> entrenamientos = entrenamientoRepository.findByStatus("pending");
        
        entrenamientos.forEach(entrenamiento -> {
            var images = imageService.getImagesForEntity("Entrenamiento", entrenamiento.getId());
            entrenamiento.getImages().addAll(images);
        });
        return entrenamientos;
        
    }
}
