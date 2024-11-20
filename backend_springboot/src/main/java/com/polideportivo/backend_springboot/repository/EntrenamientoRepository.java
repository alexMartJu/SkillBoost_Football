package com.polideportivo.backend_springboot.repository;

import com.polideportivo.backend_springboot.model.Entrenamiento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntrenamientoRepository extends JpaRepository<Entrenamiento, Long> {
    
}
