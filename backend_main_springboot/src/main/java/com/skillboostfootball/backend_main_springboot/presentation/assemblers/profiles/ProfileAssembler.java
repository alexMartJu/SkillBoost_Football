package com.skillboostfootball.backend_main_springboot.presentation.assemblers.profiles;

import com.skillboostfootball.backend_main_springboot.application.useCases.profileSuscripciones.GetActiveSuscripcionByProfileIdUseCase;
import com.skillboostfootball.backend_main_springboot.domain.entities.profiles.Profile;
import com.skillboostfootball.backend_main_springboot.domain.entities.profileSuscripciones.ProfileSuscripcion;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.profiles.response.ProfileResponse;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.profiles.response.ProfileWrapper;

import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProfileAssembler {
    
    private final GetActiveSuscripcionByProfileIdUseCase getActiveSuscripcionByProfileIdUseCase;
    
    public ProfileResponse toProfileResponse(Profile profile) {
        if (profile == null) {
            return null;
        }
        
        // Obtener la suscripción activa del perfil
        Optional<ProfileSuscripcion> activeSuscripcion = Optional.empty();
        if (profile.getId() != null) {
            activeSuscripcion = getActiveSuscripcionByProfileIdUseCase.execute(profile.getId());
        }
        
        ProfileResponse.ProfileResponseBuilder builder = ProfileResponse.builder()
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
            .entrenamientosDisponibles(profile.getEntrenamientosDisponibles());
        
        // Añadir información de suscripción si existe
        if (activeSuscripcion.isPresent()) {
            ProfileSuscripcion suscripcion = activeSuscripcion.get();
            builder
                .suscripcionStatus(suscripcion.getStatus())
                .fechaFinSuscripcion(suscripcion.getFechaFin() != null ? 
                    suscripcion.getFechaFin().format(DateTimeFormatter.ISO_DATE) : null);
            
            if (suscripcion.getSuscripcion() != null) {
                builder
                    .suscripcionNombre(suscripcion.getSuscripcion().getNombre())
                    .suscripcionSlug(suscripcion.getSuscripcion().getSlug())
                    .incluyeGraficas(suscripcion.getSuscripcion().getIncluyeGraficas())
                    .incluyeEvaluacion(suscripcion.getSuscripcion().getIncluyeEvaluacion());
            }
        }
        
        return builder.build();
    }

    public ProfileWrapper toWrapper(List<Profile> profiles) {
        List<ProfileResponse> responses = profiles.stream()
                .map(this::toProfileResponse)
                .collect(Collectors.toList());
                
        return ProfileWrapper.builder()
                .profiles(responses)
                .build();
    }

    public ProfileResponse toResponse(Profile profile) {
        return toProfileResponse(profile);
    }
}
