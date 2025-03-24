package com.skillboostfootball.backend_main_springboot.application.useCases.graficas;

import com.skillboostfootball.backend_main_springboot.domain.entities.graficas.Grafica;
import com.skillboostfootball.backend_main_springboot.domain.entities.profiles.Profile;
import com.skillboostfootball.backend_main_springboot.domain.exceptions.graficas.GraficaNotFoundException;
import com.skillboostfootball.backend_main_springboot.domain.exceptions.profiles.ProfileNotFoundException;
import com.skillboostfootball.backend_main_springboot.domain.repositories.graficas.GraficaRepository;
import com.skillboostfootball.backend_main_springboot.domain.repositories.profiles.ProfileRepository;
import com.skillboostfootball.backend_main_springboot.domain.repositories.profileSuscripciones.ProfileSuscripcionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetGraficasByNumeroSocioAndYearUseCase {
    private final GraficaRepository graficaRepository;
    private final ProfileRepository profileRepository;
    private final ProfileSuscripcionRepository profileSuscripcionRepository;
    
    @Transactional(readOnly = true)
    public List<Grafica> execute(String numeroSocio, Integer año) {
        
        Profile profile = profileRepository.findByNumeroSocio(numeroSocio)
            .orElseThrow(ProfileNotFoundException::new);
        
        //Verificar que el usuario tiene una suscripción que permite gráficas
        boolean tieneGraficas = profileSuscripcionRepository.findActiveWithSuscripcionByProfileId(profile.getId())
            .map(ps -> ps.getSuscripcion() != null && ps.getSuscripcion().getIncluyeGraficas())
            .orElse(false);
        
        if (!tieneGraficas) {
            throw new IllegalStateException("El usuario no tiene una suscripción que incluya gráficas de rendimiento");
        }
        
        //Obtener las gráficas por número de socio y año
        List<Grafica> graficas = graficaRepository.findByProfileNumeroSocioAndAño(numeroSocio, año);
        if (graficas.isEmpty()) {
            throw new GraficaNotFoundException();
        }
        
        return graficas;
    }
}
