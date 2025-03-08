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
public class GetProfileByNumeroEntrenadorUseCase {
    private final ProfileRepository profileRepository;
    private final UsuarioRepository usuarioRepository;
    
    @Transactional(readOnly = true)
    public Profile execute(String numeroEntrenador) {

        //Buscar el perfil por número de entrenador
        return profileRepository.findByNumeroEntrenador(numeroEntrenador)
            .filter(profile -> {
                Usuario usuario = usuarioRepository.findById(profile.getId()).orElseThrow(ProfileNotFoundException::new);
                return usuario != null && usuario.hasRole("entrenador");
            }).orElseThrow(ProfileNotFoundException::new);
            
    }
}
