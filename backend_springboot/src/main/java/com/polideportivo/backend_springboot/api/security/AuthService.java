package com.polideportivo.backend_springboot.api.security;

import com.polideportivo.backend_springboot.api.model.usuario.UsuarioResponse;
import com.polideportivo.backend_springboot.api.model.usuario.UsuarioAuthenticate;
import com.polideportivo.backend_springboot.api.model.usuario.UsuarioNewAccessTokenResponse;
import com.polideportivo.backend_springboot.domain.model.Usuario;

public interface AuthService {
    
    UsuarioResponse register(final Usuario user);

    Object authenticate(final UsuarioAuthenticate authenticate);

    UsuarioNewAccessTokenResponse refreshAccessToken(final String refreshToken);

    void logout(final String refreshToken);
}
