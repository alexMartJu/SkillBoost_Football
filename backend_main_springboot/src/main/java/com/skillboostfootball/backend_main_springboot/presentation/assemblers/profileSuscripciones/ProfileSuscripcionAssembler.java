package com.skillboostfootball.backend_main_springboot.presentation.assemblers.profileSuscripciones;

import com.skillboostfootball.backend_main_springboot.domain.entities.profileSuscripciones.ProfileSuscripcion;
import com.skillboostfootball.backend_main_springboot.domain.repositories.profiles.ProfileRepository;
import com.skillboostfootball.backend_main_springboot.domain.repositories.suscripciones.SuscripcionRepository;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.profileSuscripciones.response.ProfileSuscripcionResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProfileSuscripcionAssembler {
    
    private final ProfileRepository profileRepository;
    private final SuscripcionRepository suscripcionRepository;
    
    public ProfileSuscripcionResponse toResponse(ProfileSuscripcion profileSuscripcion) {
        ProfileSuscripcionResponse response = new ProfileSuscripcionResponse();
        
        response.setId(profileSuscripcion.getId());
        response.setProfileId(profileSuscripcion.getProfileId());
        response.setSuscripcionId(profileSuscripcion.getSuscripcionId());
        response.setFechaInicio(profileSuscripcion.getFechaInicio());
        response.setFechaFin(profileSuscripcion.getFechaFin());
        response.setStatus(profileSuscripcion.getStatus());
        response.setMetodoPago(profileSuscripcion.getMetodoPago());
        
        suscripcionRepository.findById(profileSuscripcion.getSuscripcionId())
            .ifPresent(suscripcion -> {
                response.setSuscripcionNombre(suscripcion.getNombre());
                response.setIncluyeGraficas(suscripcion.getIncluyeGraficas());
                response.setIncluyeEvaluacion(suscripcion.getIncluyeEvaluacion());
            });
        
        profileRepository.findById(profileSuscripcion.getProfileId())
            .ifPresent(profile -> {
                response.setEntrenamientosDisponibles(profile.getEntrenamientosDisponibles());
            });
        return response;
    }
}
