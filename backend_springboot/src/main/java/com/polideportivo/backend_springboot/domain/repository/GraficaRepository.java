package com.polideportivo.backend_springboot.domain.repository;

import com.polideportivo.backend_springboot.domain.model.Grafica;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface GraficaRepository extends JpaRepository<Grafica, Long> {
    
    List<Grafica> findByProfile_NumeroSocioAndAño(String numeroSocio, Integer año);
    
}
