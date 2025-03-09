package com.skillboostfootball.backend_main_springboot.application.useCases.entrenamientos;

import com.skillboostfootball.backend_main_springboot.application.services.images.ImageService;
import com.skillboostfootball.backend_main_springboot.application.useCases.usuarios.GetCurrentUserUseCase;
import com.skillboostfootball.backend_main_springboot.domain.entities.entrenamientos.Entrenamiento;
import com.skillboostfootball.backend_main_springboot.domain.repositories.entrenamientos.EntrenamientoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetEntrenamientosByCoachAndStatusUseCase {
    
    private final EntrenamientoRepository entrenamientoRepository;
    private final ImageService imageService;
    private final GetCurrentUserUseCase getCurrentUserUseCase;
    
    @Transactional(readOnly = true)
    public List<Entrenamiento> execute(List<String> statusList) {

        var currentUser = getCurrentUserUseCase.execute();
        Long entrenadorId = currentUser.getProfile().getId();
        List<Entrenamiento> entrenamientos = entrenamientoRepository.findByEntrenadorIdAndStatusIn(entrenadorId, statusList);
        
        entrenamientos.forEach(entrenamiento -> {
            var images = imageService.getImagesForEntity("Entrenamiento", entrenamiento.getId());
            entrenamiento.getImages().addAll(images);
        });
        return entrenamientos;
        
    }

}
