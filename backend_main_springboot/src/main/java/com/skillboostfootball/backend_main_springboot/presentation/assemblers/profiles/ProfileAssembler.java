package com.skillboostfootball.backend_main_springboot.presentation.assemblers.profiles;

import com.skillboostfootball.backend_main_springboot.domain.entities.profiles.Profile;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.profiles.response.ProfileResponse;

import org.springframework.stereotype.Component;

@Component
public class ProfileAssembler {
    
    public ProfileResponse toProfileResponse(Profile profile) {
        if (profile == null) {
            return null;
        }
        
        return ProfileResponse.builder()
            .numeroSocio(profile.getNumeroSocio())
            .nombre(profile.getNombre())
            .apellidos(profile.getApellidos())
            .bio(profile.getBio())
            .image(profile.getImage())
            .edad(profile.getEdad())
            .esMenor(profile.getEsMenor())
            .posicionPreferida(profile.getPosicionPreferida())
            .clubOrigen(profile.getClubOrigen())
            .numeroEntrenador(profile.getNumeroEntrenador())
            .especialidad(profile.getEspecialidad())
            .experienciaAnios(profile.getExperienciaAnios())
            .certificaciones(profile.getCertificaciones())
            .organizacionOrigen(profile.getOrganizacionOrigen())
            .build();
    }
}
