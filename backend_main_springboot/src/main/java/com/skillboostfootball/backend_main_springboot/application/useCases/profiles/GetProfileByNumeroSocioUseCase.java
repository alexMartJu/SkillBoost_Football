package com.skillboostfootball.backend_main_springboot.application.useCases.profiles;

import com.skillboostfootball.backend_main_springboot.domain.entities.profiles.Profile;
import com.skillboostfootball.backend_main_springboot.domain.entities.usuarios.Usuario;
import com.skillboostfootball.backend_main_springboot.domain.exceptions.profiles.ProfileNotFoundException;
import com.skillboostfootball.backend_main_springboot.domain.repositories.profiles.ProfileRepository;
import com.skillboostfootball.backend_main_springboot.domain.repositories.usuarios.UsuarioRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetProfileByNumeroSocioUseCase {
    private final ProfileRepository profileRepository;
    private final UsuarioRepository usuarioRepository;

    
    @Transactional(readOnly = true)
    public Profile execute(String numeroSocio) {

        Profile profile = profileRepository.findByNumeroSocio(numeroSocio).orElseThrow(ProfileNotFoundException::new);     
        Usuario usuario = usuarioRepository.findById(profile.getId()).orElseThrow(ProfileNotFoundException::new);
        
        // Verificar que no sea admin ni entrenador
        if (usuario != null && (usuario.hasRole("admin") || usuario.hasRole("entrenador"))) {
            throw new ProfileNotFoundException();
        }
        return profile;
        
    }
}
