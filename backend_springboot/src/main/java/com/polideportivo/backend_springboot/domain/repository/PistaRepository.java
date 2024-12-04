package com.polideportivo.backend_springboot.domain.repository;

import com.polideportivo.backend_springboot.domain.model.Pista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface PistaRepository extends JpaRepository<Pista, Long> {
    List<Pista> findByDeletedAtIsNull();

    Optional<Pista> findBySlugAndDeletedAtIsNull(String slug);

    Page<Pista> findByDeletedAtIsNull(Pageable pageable);
}
