package com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl.usuarios;

import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.usuarios.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SpringDataUsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    Optional<UsuarioEntity> findByEmail(String email);
    boolean existsByEmail(String email);
    
}