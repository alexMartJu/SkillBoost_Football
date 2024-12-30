package com.polideportivo.backend_springboot.domain.repository;

import com.polideportivo.backend_springboot.domain.model.InscripcionEntrenamiento;
import com.polideportivo.backend_springboot.domain.model.InscripcionEntrenamientoId;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscripcionEntrenamientoRepository extends JpaRepository<InscripcionEntrenamiento, InscripcionEntrenamientoId> {

    long countByEntrenamientoId(Long entrenamientoId);
}
