package com.polideportivo.backend_springboot.domain.service;

import com.polideportivo.backend_springboot.domain.model.Profile;
import com.polideportivo.backend_springboot.domain.model.Usuario;

public interface ProfileService {

    Profile save(final Profile profile);

    Profile createNewProfile(final Usuario user, final String nombre, final String apellidos);
}
