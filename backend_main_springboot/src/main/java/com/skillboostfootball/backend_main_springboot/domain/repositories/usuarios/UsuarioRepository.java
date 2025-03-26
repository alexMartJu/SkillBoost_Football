package com.skillboostfootball.backend_main_springboot.domain.repositories.usuarios;

import com.skillboostfootball.backend_main_springboot.domain.entities.usuarios.Usuario;
import java.util.Optional;

public interface UsuarioRepository {

    Optional<Usuario> findById(Long id);
    Optional<Usuario> findByEmail(String email);
    boolean existsByEmail(String email);
    Usuario save(Usuario usuario);
    void delete(Usuario usuario);
    
}
