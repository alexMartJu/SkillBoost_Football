package com.skillboostfootball.backend_main_springboot.application.useCases.profiles;

import com.skillboostfootball.backend_main_springboot.domain.entities.profiles.Profile;
import com.skillboostfootball.backend_main_springboot.domain.repositories.profiles.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllSocialHelpPlayerUseCase {
    private final ProfileRepository profileRepository;
    
    @Transactional(readOnly = true)
    public List<Profile> execute() {
        return profileRepository.findByUsuarioHasRoleName("jugador_social");
    }
}