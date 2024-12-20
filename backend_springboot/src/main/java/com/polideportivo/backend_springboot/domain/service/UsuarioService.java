package com.polideportivo.backend_springboot.domain.service;

import com.polideportivo.backend_springboot.domain.model.Usuario;
import com.polideportivo.backend_springboot.domain.model.Profile;

public interface UsuarioService {
    
    Usuario getCurrentUser();

    Usuario getByEmail(final String email);

    Usuario save(final Usuario user, final Profile profile);

    Usuario saveUpdate(final Usuario user, final Profile profile);

    void setRefreshToken(final Usuario user, final String token);
}
