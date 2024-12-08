package com.polideportivo.backend_springboot.api.security;

import com.polideportivo.backend_springboot.api.assembler.UsuarioAssembler;
import com.polideportivo.backend_springboot.api.model.usuario.UsuarioResponse;
import com.polideportivo.backend_springboot.domain.model.Usuario;
import lombok.RequiredArgsConstructor;


import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    
    private final UsuarioAssembler userAssembler;

    public UsuarioResponse register(Usuario user) {
        return userAssembler.toResponse(user);
    }
}
