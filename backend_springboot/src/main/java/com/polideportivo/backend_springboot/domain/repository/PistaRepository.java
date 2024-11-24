package com.polideportivo.backend_springboot.domain.repository;

import com.polideportivo.backend_springboot.domain.model.Pista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface PistaRepository extends JpaRepository<Pista, Long>, JpaSpecificationExecutor<Pista> {
}
