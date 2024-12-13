package com.polideportivo.backend_springboot.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.polideportivo.backend_springboot.domain.model.Entrenador;

@Repository
public interface EntrenadorRepository extends JpaRepository<Entrenador, Long> {
    
    boolean existsByEmail(String email);

}
