package com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl.pistas;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.pistas.PistaEntity;

import java.util.List;
import java.util.Optional;

public interface SpringDataPistaRepository extends JpaRepository<PistaEntity, Long> {
    List<PistaEntity> findByDeletedAtIsNull();
    Optional<PistaEntity> findBySlugAndDeletedAtIsNull(String slug);
}
