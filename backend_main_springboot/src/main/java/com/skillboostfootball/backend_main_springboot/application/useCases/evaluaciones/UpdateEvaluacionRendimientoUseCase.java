package com.skillboostfootball.backend_main_springboot.application.useCases.evaluaciones;

import com.skillboostfootball.backend_main_springboot.application.useCases.usuarios.GetCurrentUserUseCase;
import com.skillboostfootball.backend_main_springboot.domain.entities.evaluaciones.EvaluacionRendimiento;
import com.skillboostfootball.backend_main_springboot.domain.entities.profiles.Profile;
import com.skillboostfootball.backend_main_springboot.domain.entities.usuarios.Usuario;
import com.skillboostfootball.backend_main_springboot.domain.exceptions.entrenamientos.EntrenamientoNotFoundException;
import com.skillboostfootball.backend_main_springboot.domain.exceptions.evaluaciones.EvaluacionNotFoundException;
import com.skillboostfootball.backend_main_springboot.domain.exceptions.profiles.ProfileNotFoundException;
import com.skillboostfootball.backend_main_springboot.domain.repositories.entrenamientos.EntrenamientoRepository;
import com.skillboostfootball.backend_main_springboot.domain.repositories.evaluaciones.EvaluacionRendimientoRepository;
import com.skillboostfootball.backend_main_springboot.domain.repositories.profiles.ProfileRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateEvaluacionRendimientoUseCase {
    
    private final EvaluacionRendimientoRepository evaluacionRepository;
    private final ProfileRepository profileRepository;
    private final EntrenamientoRepository entrenamientoRepository;
    private final GetCurrentUserUseCase getCurrentUserUseCase;
    
    @Transactional
    public EvaluacionRendimiento execute(String numeroSocio, String entrenamientoSlug, Integer puntuacion, String comentarios, String areasMejora) {

        //Obtener el usuario actual (entrenador)
        Usuario currentUser = getCurrentUserUseCase.execute();
        Long entrenadorId = currentUser.getProfile().getId();

        //Buscar el perfil por número de socio
        Profile profile = profileRepository.findByNumeroSocio(numeroSocio).orElseThrow((ProfileNotFoundException::new));
        Long profileId = profile.getId();
        
        //Verificar que el entrenamiento existe
        var entrenamiento = entrenamientoRepository.findBySlug(entrenamientoSlug).orElseThrow(EntrenamientoNotFoundException::new);
        
        if (!entrenamiento.getEntrenadorId().equals(entrenadorId)) {
            throw new SecurityException("No tienes permiso para actualizar evaluaciones de este entrenamiento");
        }
        //Buscar la evaluación existente
        EvaluacionRendimiento evaluacion = evaluacionRepository.findByProfileIdAndEntrenamientoId(profileId, entrenamiento.getId()).orElseThrow(EvaluacionNotFoundException::new);
        //Actualizar la evaluación
        evaluacion.update(puntuacion, comentarios, areasMejora);
        
        return evaluacionRepository.save(evaluacion);
        
    }
}
