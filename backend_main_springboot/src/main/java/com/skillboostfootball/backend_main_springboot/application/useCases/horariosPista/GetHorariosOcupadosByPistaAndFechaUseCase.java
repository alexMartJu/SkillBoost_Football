package com.skillboostfootball.backend_main_springboot.application.useCases.horariosPista;

import com.skillboostfootball.backend_main_springboot.domain.entities.horariosPista.HorarioPista;
import com.skillboostfootball.backend_main_springboot.domain.entities.pistas.Pista;
import com.skillboostfootball.backend_main_springboot.domain.exceptions.pistas.PistaNotFoundException;
import com.skillboostfootball.backend_main_springboot.domain.repositories.horariosPista.HorarioPistaRepository;
import com.skillboostfootball.backend_main_springboot.domain.repositories.pistas.PistaRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetHorariosOcupadosByPistaAndFechaUseCase {
    
    private final HorarioPistaRepository horarioPistaRepository;
    private final PistaRepository pistaRepository;
    
    @Transactional(readOnly = true)
    public List<HorarioPista> execute(String pistaSlug, LocalDate fecha) {
        
        Pista pista = pistaRepository.findBySlug(pistaSlug).orElseThrow(PistaNotFoundException::new);
        
        Long pistaId = pista.getId();
        log.debug("Buscando horarios ocupados para la pista {} (ID: {}) en la fecha {}", pista.getNombre(), pistaId, fecha);
        //Obtener todos los horarios ocupados de esa pista en esa fecha
        return horarioPistaRepository.findOcupadosByPistaIdAndFecha(pistaId, fecha);

    }
}
