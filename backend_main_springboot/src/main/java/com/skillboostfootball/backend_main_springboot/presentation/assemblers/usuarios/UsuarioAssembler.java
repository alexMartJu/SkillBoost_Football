package com.skillboostfootball.backend_main_springboot.presentation.assemblers.usuarios;

import com.skillboostfootball.backend_main_springboot.domain.entities.usuarios.Usuario;
import com.skillboostfootball.backend_main_springboot.domain.exceptions.usuarios.UsuarioNotFoundException;
import com.skillboostfootball.backend_main_springboot.domain.repositories.usuarios.UsuarioRepository;
import com.skillboostfootball.backend_main_springboot.presentation.assemblers.profiles.ProfileAssembler;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.auth.response.AuthResponse;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.usuarios.response.UserResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UsuarioAssembler {
    private final UsuarioRepository usuarioRepository;
    private final ProfileAssembler profileAssembler;
    
    public UserResponse toUserResponse(Usuario usuario) {
        return UserResponse.builder()
            .id(usuario.getId())
            .email(usuario.getEmail())
            .tipoUsuario(usuario.getTipoUsuario())
            .status(usuario.getStatus())
            .profile(usuario.getProfile() != null ? 
                 profileAssembler.toProfileResponse(usuario.getProfile()) : null)
            .roles(usuario.getRoles().stream()
                .map(role -> role.getName())
                .collect(Collectors.toList()))
            .build();
    }
    
    public AuthResponse toAuthResponse(Usuario usuario, String accessToken, String refreshToken) {
        return AuthResponse.builder()
            .accessToken(accessToken)
            .refreshToken(refreshToken)
            .user(toUserResponse(usuario))
            .build();
    }
    
    public Usuario findUserByEmail(String email) {
        return usuarioRepository.findByEmail(email).orElseThrow(UsuarioNotFoundException::new);
    }
}
