package com.skillboostfootball.backend_main_springboot.application.useCases.suscripciones;

import com.skillboostfootball.backend_main_springboot.application.useCases.usuarios.GetCurrentUserUseCase;
import com.skillboostfootball.backend_main_springboot.domain.entities.profileSuscripciones.ProfileSuscripcion;
import com.skillboostfootball.backend_main_springboot.domain.entities.suscripciones.Suscripcion;
import com.skillboostfootball.backend_main_springboot.domain.entities.usuarios.Usuario;
import com.skillboostfootball.backend_main_springboot.domain.exceptions.suscripciones.SuscripcionNotFoundException;
import com.skillboostfootball.backend_main_springboot.domain.repositories.profileSuscripciones.ProfileSuscripcionRepository;
import com.skillboostfootball.backend_main_springboot.domain.repositories.suscripciones.SuscripcionRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetSuscripcionActivaUseCase {
    
    private final ProfileSuscripcionRepository profileSuscripcionRepository;
    private final SuscripcionRepository suscripcionRepository;
    private final GetCurrentUserUseCase getCurrentUserUseCase;

    @Transactional(readOnly = true)
    public Suscripcion execute() {

        Usuario usuario = getCurrentUserUseCase.execute();
        Long profileId = usuario.getProfile().getId();
        
        ProfileSuscripcion profileSuscripcion = profileSuscripcionRepository.findActiveByProfileId(profileId).orElseThrow(SuscripcionNotFoundException::new);
        
        return suscripcionRepository.findById(profileSuscripcion.getSuscripcionId()).orElseThrow(SuscripcionNotFoundException::new);

    }
}
