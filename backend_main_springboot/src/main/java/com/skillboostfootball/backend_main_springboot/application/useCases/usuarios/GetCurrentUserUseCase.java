package com.skillboostfootball.backend_main_springboot.application.useCases.usuarios;

import com.skillboostfootball.backend_main_springboot.application.security.AuthUtils;
import com.skillboostfootball.backend_main_springboot.domain.entities.usuarios.Usuario;
import com.skillboostfootball.backend_main_springboot.domain.exceptions.usuarios.UsuarioNotFoundException;
import com.skillboostfootball.backend_main_springboot.domain.repositories.usuarios.UsuarioRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetCurrentUserUseCase {
    private final UsuarioRepository usuarioRepository;
    private final AuthUtils authUtils;
    
    @Transactional(readOnly = true)
    public Usuario execute() {

        String email = authUtils.getCurrentUserEmail();
        return usuarioRepository.findByEmail(email).orElseThrow(UsuarioNotFoundException::new);
        
    }
}
