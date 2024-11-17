package com.polideportivo.backend_springboot.repository;

import com.polideportivo.backend_springboot.model.Deporte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeporteRepository extends JpaRepository<Deporte, Long> {
    
}
