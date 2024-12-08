package com.polideportivo.backend_springboot.api.security;

import com.polideportivo.backend_springboot.api.model.usuario.UsuarioResponse;
import com.polideportivo.backend_springboot.domain.model.Usuario;

public interface AuthService {
    
    UsuarioResponse register(final Usuario user);
}
