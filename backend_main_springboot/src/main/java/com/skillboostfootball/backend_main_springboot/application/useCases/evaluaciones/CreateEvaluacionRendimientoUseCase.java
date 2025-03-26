package com.skillboostfootball.backend_main_springboot.application.useCases.evaluaciones;

import com.skillboostfootball.backend_main_springboot.application.useCases.usuarios.GetCurrentUserUseCase;
import com.skillboostfootball.backend_main_springboot.domain.entities.evaluaciones.EvaluacionRendimiento;
import com.skillboostfootball.backend_main_springboot.domain.entities.profiles.Profile;
import com.skillboostfootball.backend_main_springboot.domain.entities.usuarios.Usuario;
import com.skillboostfootball.backend_main_springboot.domain.exceptions.entrenamientos.EntrenamientoNotFoundException;
import com.skillboostfootball.backend_main_springboot.domain.exceptions.profiles.ProfileNotFoundException;
import com.skillboostfootball.backend_main_springboot.domain.repositories.entrenamientos.EntrenamientoRepository;
import com.skillboostfootball.backend_main_springboot.domain.repositories.evaluaciones.EvaluacionRendimientoRepository;
import com.skillboostfootball.backend_main_springboot.domain.repositories.profiles.ProfileRepository;
import com.skillboostfootball.backend_main_springboot.domain.repositories.profileSuscripciones.ProfileSuscripcionRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CreateEvaluacionRendimientoUseCase {
    
    private final EvaluacionRendimientoRepository evaluacionRepository;
    private final EntrenamientoRepository entrenamientoRepository;
    private final ProfileRepository profileRepository;
    private final ProfileSuscripcionRepository profileSuscripcionRepository;
    private final GetCurrentUserUseCase getCurrentUserUseCase;
    
    @Transactional
    public EvaluacionRendimiento execute(String numeroSocio, String entrenamientoSlug, Integer puntuacion, String comentarios, String areasMejora) {
        
        Usuario currentUser = getCurrentUserUseCase.execute();
        Long entrenadorId = currentUser.getProfile().getId();
        
        //Buscar el perfil por número de socio
        Profile profile = profileRepository.findByNumeroSocio(numeroSocio).orElseThrow((ProfileNotFoundException::new));
        Long profileId = profile.getId();
        //Verificar que el entrenamiento existe
        var entrenamiento = entrenamientoRepository.findBySlug(entrenamientoSlug).orElseThrow(EntrenamientoNotFoundException::new);
        
        if (!entrenamiento.getEntrenadorId().equals(entrenadorId)) {
            throw new SecurityException("No tienes permiso para evaluar este entrenamiento");
        }
        // Verificar si ya existe una evaluación para este perfil y entrenamiento
        Optional<EvaluacionRendimiento> existingEvaluacion = evaluacionRepository.findByProfileIdAndEntrenamientoId(profileId, entrenamiento.getId());
        
        if (existingEvaluacion.isPresent()) {
            //Si ya existe, actualizar en lugar de crear una nueva
            EvaluacionRendimiento evaluacion = existingEvaluacion.get();
            evaluacion.update(puntuacion, comentarios, areasMejora);
            return evaluacionRepository.save(evaluacion);
        }

        //Verificar que el usuario tiene una suscripción que permite evaluaciones
        boolean tieneEvaluaciones = profileSuscripcionRepository.findActiveWithSuscripcionByProfileId(profileId)
            .map(ps -> ps.getSuscripcion() != null && ps.getSuscripcion().getIncluyeEvaluacion())
            .orElse(false);
        
        if (!tieneEvaluaciones) {
            throw new IllegalStateException("El usuario no tiene una suscripción que incluya evaluaciones de rendimiento");
        }
        
        //Crear la evaluación
        EvaluacionRendimiento evaluacion = EvaluacionRendimiento.builder()
                .profileId(profileId)
                .entrenamientoId(entrenamiento.getId())
                .fechaEvaluacion(LocalDate.now())
                .puntuacion(puntuacion)
                .comentarios(comentarios)
                .areasMejora(areasMejora)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        return evaluacionRepository.save(evaluacion);

    }
}
