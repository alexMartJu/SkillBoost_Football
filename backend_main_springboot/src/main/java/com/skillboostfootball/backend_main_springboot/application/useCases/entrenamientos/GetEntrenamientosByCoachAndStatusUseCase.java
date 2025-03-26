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

            //Cargar imágenes del subtipo de tecnificación si existe
            if (entrenamiento.getSubtipoTecnificacion() != null) {
                var subtipoImages = imageService.getImagesForEntity("SubtipoTecnificacion", entrenamiento.getSubtipoTecnificacion().getId());
                entrenamiento.getSubtipoTecnificacion().getImages().addAll(subtipoImages);
            }
            
            //Cargar imágenes de la pista si existe
            if (entrenamiento.getHorarioPista() != null && entrenamiento.getHorarioPista().getPista() != null) {
                var pistaImages = imageService.getImagesForEntity("Pista", entrenamiento.getHorarioPista().getPista().getId());
                entrenamiento.getHorarioPista().getPista().getImages().addAll(pistaImages);
            }
        });
        return entrenamientos;
        
    }

}
