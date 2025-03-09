package com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl.horariosPista;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.horariosPista.HorarioPistaEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface SpringDataHorarioPistaRepository extends JpaRepository<HorarioPistaEntity, Long> {
    
    @Query("SELECT h FROM HorarioPistaEntity h WHERE h.pista.id = :pistaId " + "AND h.deletedAt IS NULL " +
           "AND h.fechaInicio >= :startOfDay AND h.fechaInicio < :endOfDay " + "AND h.entrenamiento IS NOT NULL")
    List<HorarioPistaEntity> findOcupadosByPistaIdAndFecha(@Param("pistaId") Long pistaId, @Param("startOfDay") LocalDateTime startOfDay,
        @Param("endOfDay") LocalDateTime endOfDay);
    
    @Query("SELECT h FROM HorarioPistaEntity h WHERE h.pista.id = :pistaId " + "AND h.deletedAt IS NULL " + "AND h.entrenamiento IS NOT NULL " +
           "AND (" +
           "(h.fechaInicio >= :fechaInicio AND h.fechaInicio < :fechaFin) OR " +
           "(h.fechaFin > :fechaInicio AND h.fechaFin <= :fechaFin) OR " +
           "(h.fechaInicio <= :fechaInicio AND h.fechaFin >= :fechaFin)" + ")")
    List<HorarioPistaEntity> findOverlappingHorarios(@Param("pistaId") Long pistaId, @Param("fechaInicio") LocalDateTime fechaInicio,
        @Param("fechaFin") LocalDateTime fechaFin);
}
