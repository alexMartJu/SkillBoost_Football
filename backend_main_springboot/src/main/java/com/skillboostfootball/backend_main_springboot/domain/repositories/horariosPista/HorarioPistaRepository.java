package com.skillboostfootball.backend_main_springboot.domain.repositories.horariosPista;

import com.skillboostfootball.backend_main_springboot.domain.entities.horariosPista.HorarioPista;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface HorarioPistaRepository {

    List<HorarioPista> findOcupadosByPistaIdAndFecha(Long pistaId, LocalDate fecha);
    List<HorarioPista> findOverlappingHorarios(Long pistaId, LocalDateTime fechaInicio, LocalDateTime fechaFin);
    HorarioPista save(HorarioPista horarioPista);
    Optional<HorarioPista> findById(Long id);
    boolean existsById(Long id);
    
}
