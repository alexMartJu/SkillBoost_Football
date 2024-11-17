package com.polideportivo.backend_springboot.repository;

import com.polideportivo.backend_springboot.model.Clase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaseRepository extends JpaRepository<Clase, Long> {
    
}
