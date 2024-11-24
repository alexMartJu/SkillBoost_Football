package com.polideportivo.backend_springboot.domain.repository;

import com.polideportivo.backend_springboot.domain.model.Deporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface DeporteRepository extends JpaRepository<Deporte, Long>, JpaSpecificationExecutor<Deporte> {
    List<Deporte> findByDeletedAtIsNull();  // Busca los deportes que no están eliminados
    Optional<Deporte> findBySlugAndDeletedAtIsNull(String slug); // Busca un deporte por slug, solo si no está eliminado
}
