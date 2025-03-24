package com.skillboostfootball.backend_main_springboot.application.useCases.profileSuscripciones;

import com.skillboostfootball.backend_main_springboot.domain.entities.profileSuscripciones.ProfileSuscripcion;
import com.skillboostfootball.backend_main_springboot.domain.repositories.profileSuscripciones.ProfileSuscripcionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetActiveSuscripcionByProfileIdUseCase {
    
    private final ProfileSuscripcionRepository profileSuscripcionRepository;
    
    @Transactional(readOnly = true)
    public Optional<ProfileSuscripcion> execute(Long profileId) {
        return profileSuscripcionRepository.findActiveWithSuscripcionByProfileId(profileId);
    }
}
