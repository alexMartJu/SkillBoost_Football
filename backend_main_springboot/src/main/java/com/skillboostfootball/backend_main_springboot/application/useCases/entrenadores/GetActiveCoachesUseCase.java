package com.skillboostfootball.backend_main_springboot.application.useCases.entrenadores;

import com.skillboostfootball.backend_main_springboot.domain.entities.profiles.Profile;
import com.skillboostfootball.backend_main_springboot.domain.entities.usuarios.Usuario;
import com.skillboostfootball.backend_main_springboot.domain.exceptions.profiles.ProfileNotFoundException;
import com.skillboostfootball.backend_main_springboot.domain.repositories.profiles.ProfileRepository;
import com.skillboostfootball.backend_main_springboot.domain.repositories.usuarios.UsuarioRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetActiveCoachesUseCase {

    private final ProfileRepository profileRepository;
    private final UsuarioRepository usuarioRepository;
    
    @Transactional(readOnly = true)
    public List<Profile> execute() {

        //Obtener todos los perfiles con numeroEntrenador no nulo
        List<Profile> entrenadores = profileRepository.findByNumeroEntrenadorIsNotNull();
        //Filtrar solo los que estén activos
        List<Profile> entrenadoresActivos = new ArrayList<>();
        
        for (Profile profile : entrenadores) {
            Optional<Usuario> usuarioOpt = usuarioRepository.findById(profile.getId());
            
            if (usuarioOpt.isPresent()) {
                Usuario usuario = usuarioOpt.get();
                //Verificar que el usuario sea un entrenador y esté activo
                if (usuario.isActive() && usuario.hasRole("entrenador")) {
                    entrenadoresActivos.add(profile);
                }
            }

        }
        
        if (entrenadoresActivos.isEmpty()) {
            throw new ProfileNotFoundException();
        }
        return entrenadoresActivos;
        
    }
}


