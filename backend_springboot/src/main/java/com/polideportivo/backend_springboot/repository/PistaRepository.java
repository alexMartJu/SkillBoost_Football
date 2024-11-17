package com.polideportivo.backend_springboot.repository;

import com.polideportivo.backend_springboot.model.Pista;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PistaRepository extends JpaRepository<Pista, Long> {
    
}
