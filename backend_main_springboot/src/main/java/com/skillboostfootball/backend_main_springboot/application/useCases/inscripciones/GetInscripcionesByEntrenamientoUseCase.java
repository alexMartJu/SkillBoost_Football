package com.skillboostfootball.backend_main_springboot.application.useCases.inscripciones;

import com.skillboostfootball.backend_main_springboot.application.useCases.usuarios.GetCurrentUserUseCase;
import com.skillboostfootball.backend_main_springboot.domain.entities.reservas.Reserva;
import com.skillboostfootball.backend_main_springboot.domain.entities.usuarios.Usuario;
import com.skillboostfootball.backend_main_springboot.domain.exceptions.entrenamientos.EntrenamientoNotFoundException;
import com.skillboostfootball.backend_main_springboot.domain.repositories.entrenamientos.EntrenamientoRepository;
import com.skillboostfootball.backend_main_springboot.domain.repositories.inscripciones.InscripcionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetInscripcionesByEntrenamientoUseCase {
    
    private final InscripcionRepository inscripcionRepository;
    private final EntrenamientoRepository entrenamientoRepository;
    private final GetCurrentUserUseCase getCurrentUserUseCase;
    
    @Transactional(readOnly = true)
    public List<Reserva> execute(String entrenamientoSlug) {

        //Obtener el usuario actual (entrenador)
        Usuario currentUser = getCurrentUserUseCase.execute();
        Long entrenadorId = currentUser.getProfile().getId();
        
        //Verificar que el entrenamiento existe y pertenece al entrenador
        var entrenamiento = entrenamientoRepository.findBySlug(entrenamientoSlug).orElseThrow(EntrenamientoNotFoundException::new);
        if (!entrenamiento.getEntrenadorId().equals(entrenadorId)) {
            throw new SecurityException("No tienes permiso para ver las inscripciones de este entrenamiento");
        }
        
        //Obtener las inscripciones del entrenamiento
        return inscripcionRepository.findByEntrenamientoId(entrenamiento.getId());

    }
}
