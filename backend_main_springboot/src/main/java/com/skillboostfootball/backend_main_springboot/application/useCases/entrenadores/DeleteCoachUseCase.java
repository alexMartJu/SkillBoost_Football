package com.skillboostfootball.backend_main_springboot.application.useCases.entrenadores;

import com.skillboostfootball.backend_main_springboot.domain.exceptions.profiles.ProfileNotFoundException;
import com.skillboostfootball.backend_main_springboot.domain.repositories.profiles.ProfileRepository;
import com.skillboostfootball.backend_main_springboot.domain.repositories.usuarios.UsuarioRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteCoachUseCase {
    
    private final ProfileRepository profileRepository;
    private final UsuarioRepository usuarioRepository;
    
    @Transactional
    public void execute(String numeroEntrenador) {

        //Buscar el perfil del entrenador por su número
        var profileOpt = profileRepository.findByNumeroEntrenador(numeroEntrenador);
        if (profileOpt.isEmpty()) {
            throw new ProfileNotFoundException();
        }
        
        var profile = profileOpt.get();

        //Buscar el usuario asociado
        var usuarioOpt = usuarioRepository.findById(profile.getId());
        if (usuarioOpt.isEmpty() || !usuarioOpt.get().hasRole("entrenador")) {
            throw new ProfileNotFoundException();
        }
        
        var usuario = usuarioOpt.get();
        //Desactivar el usuario (soft delete)
        usuario.deactivate();
        LocalDateTime now = LocalDateTime.now();
        profile.delete(now);
        usuarioRepository.save(usuario);
        profileRepository.save(profile);

    }
}
