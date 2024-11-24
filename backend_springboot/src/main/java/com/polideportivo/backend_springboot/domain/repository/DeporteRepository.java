package com.polideportivo.backend_springboot.domain.repository;

import com.polideportivo.backend_springboot.domain.model.Deporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeporteRepository extends JpaRepository<Deporte, Long>, JpaSpecificationExecutor<Deporte> {
    Optional<Deporte> findBySlug(String slug);
}
