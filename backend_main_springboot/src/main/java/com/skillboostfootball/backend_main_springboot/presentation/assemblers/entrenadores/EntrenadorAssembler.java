package com.skillboostfootball.backend_main_springboot.presentation.assemblers.entrenadores;

import com.skillboostfootball.backend_main_springboot.domain.entities.profiles.Profile;
import com.skillboostfootball.backend_main_springboot.domain.entities.usuarios.Usuario;
import com.skillboostfootball.backend_main_springboot.domain.repositories.usuarios.UsuarioRepository;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.entrenadores.response.EntrenadorResponse;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.entrenadores.response.EntrenadorWrapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EntrenadorAssembler {
    private final UsuarioRepository usuarioRepository;
    
    public EntrenadorResponse toResponse(Profile profile) {
        //Obtener el usuario asociado al perfil
        Optional<Usuario> usuario = usuarioRepository.findById(profile.getId());
        String email = usuario.isPresent() ? usuario.get().getEmail() : "";
        
        return EntrenadorResponse.builder()
            .id(profile.getId())
            .numeroEntrenador(profile.getNumeroEntrenador())
            .nombre(profile.getNombre())
            .apellidos(profile.getApellidos())
            .email(email)
            .especialidad(profile.getEspecialidad())
            .experienciaAnios(profile.getExperienciaAnios())
            .certificaciones(profile.getCertificaciones())
            .image(profile.getImage())
            .build();
    }
    
    public EntrenadorWrapper toWrapper(List<Profile> entrenadores) {
        List<EntrenadorResponse> responseList = entrenadores.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
                
        return EntrenadorWrapper.builder()
                .entrenadores(responseList)
                .build();
    }
}
