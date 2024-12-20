package com.polideportivo.backend_springboot.api.assembler;

import com.polideportivo.backend_springboot.api.model.usuario.UsuarioNewAccessTokenResponse;
import com.polideportivo.backend_springboot.api.model.usuario.UsuarioRegister;
import com.polideportivo.backend_springboot.api.model.usuario.UsuarioResponse;
import com.polideportivo.backend_springboot.api.model.usuario.UsuarioUpdate;
import com.polideportivo.backend_springboot.domain.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class UsuarioAssembler {
    
    private final ModelMapper modelMapper;

    public UsuarioResponse toResponse(Usuario user) {
        return UsuarioResponse.builder()
                .email(user.getEmail())
                .nombre(user.getProfile().getNombre())
                .bio(user.getProfile().getBio())
                .numeroSocio(user.getProfile().getNumeroSocio())
                .image(user.getProfile().getImage())
                .apellidos(user.getProfile().getApellidos())
                .edad(user.getProfile().getEdad())
                .build();
    }

    public Usuario toEntity(UsuarioRegister register) {
        return modelMapper.map(register, Usuario.class);
    }

    public void copyToEntity(UsuarioUpdate update, Usuario user) {
        modelMapper.map(update, user);
    }

    public UsuarioNewAccessTokenResponse toNewAccessTokenResponse(String token) {
        return UsuarioNewAccessTokenResponse.builder()
                .token(token)
                .build();
    }
}
