package com.skillboostfootball.backend_main_springboot.application.useCases.inscripciones;

import com.skillboostfootball.backend_main_springboot.application.useCases.usuarios.GetCurrentUserUseCase;
import com.skillboostfootball.backend_main_springboot.domain.entities.reservas.Reserva;
import com.skillboostfootball.backend_main_springboot.domain.entities.usuarios.Usuario;
import com.skillboostfootball.backend_main_springboot.domain.repositories.inscripciones.InscripcionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllInscripcionesByEntrenadorUseCase {
    
    private final InscripcionRepository inscripcionRepository;
    private final GetCurrentUserUseCase getCurrentUserUseCase;
    
    @Transactional(readOnly = true)
    public List<Reserva> execute() {

        //Obtener el usuario actual (entrenador)
        Usuario currentUser = getCurrentUserUseCase.execute();
        Long entrenadorId = currentUser.getProfile().getId();
        
        //Obtener todas las inscripciones de los entrenamientos del entrenador
        return inscripcionRepository.findByEntrenamientoEntrenadorId(entrenadorId);

    }
}
