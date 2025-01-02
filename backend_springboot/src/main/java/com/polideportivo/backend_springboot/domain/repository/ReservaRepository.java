package com.polideportivo.backend_springboot.domain.repository;

import com.polideportivo.backend_springboot.domain.model.Reserva;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    
    boolean existsByPistaIdAndHorarioIdAndFecha(Long pistaId, Long horarioId, LocalDate fecha);

    Optional<Reserva> findByPistaIdAndHorarioIdAndFecha(Long pistaId, Long horarioId, LocalDate fecha);
}
