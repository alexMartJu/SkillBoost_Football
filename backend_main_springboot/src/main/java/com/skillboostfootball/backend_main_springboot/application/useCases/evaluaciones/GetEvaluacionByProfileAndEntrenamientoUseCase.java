package com.skillboostfootball.backend_main_springboot.application.useCases.evaluaciones;

import com.skillboostfootball.backend_main_springboot.domain.entities.evaluaciones.EvaluacionRendimiento;
import com.skillboostfootball.backend_main_springboot.domain.exceptions.entrenamientos.EntrenamientoNotFoundException;
import com.skillboostfootball.backend_main_springboot.domain.exceptions.evaluaciones.EvaluacionNotFoundException;
import com.skillboostfootball.backend_main_springboot.domain.repositories.entrenamientos.EntrenamientoRepository;
import com.skillboostfootball.backend_main_springboot.domain.repositories.evaluaciones.EvaluacionRendimientoRepository;
import com.skillboostfootball.backend_main_springboot.domain.repositories.profiles.ProfileRepository;
import com.skillboostfootball.backend_main_springboot.domain.entities.profiles.Profile;
import com.skillboostfootball.backend_main_springboot.domain.exceptions.profiles.ProfileNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetEvaluacionByProfileAndEntrenamientoUseCase {
    
    private final EvaluacionRendimientoRepository evaluacionRepository;
    private final ProfileRepository profileRepository;
    private final EntrenamientoRepository entrenamientoRepository;
    
    @Transactional(readOnly = true)
    public EvaluacionRendimiento execute(String numeroSocio, String entrenamientoSlug) {

        //Buscar el perfil por número de socio
        Profile profile = profileRepository.findByNumeroSocio(numeroSocio).orElseThrow((ProfileNotFoundException::new));
        Long profileId = profile.getId();
        
        //Verificar que el entrenamiento existe
        var entrenamiento = entrenamientoRepository.findBySlug(entrenamientoSlug).orElseThrow(EntrenamientoNotFoundException::new);
        //Buscar la evaluación
        return evaluacionRepository.findByProfileIdAndEntrenamientoId(profileId, entrenamiento.getId()).orElseThrow(EvaluacionNotFoundException::new);
        
    }
}
