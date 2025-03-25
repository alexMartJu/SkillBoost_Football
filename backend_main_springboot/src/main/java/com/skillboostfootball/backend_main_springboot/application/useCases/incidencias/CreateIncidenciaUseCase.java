package com.skillboostfootball.backend_main_springboot.application.useCases.incidencias;

import com.skillboostfootball.backend_main_springboot.domain.entities.incidencias.Incidencia;
import com.skillboostfootball.backend_main_springboot.domain.repositories.incidencias.IncidenciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CreateIncidenciaUseCase {
    private final IncidenciaRepository incidenciaRepository;
    
    @Transactional
    public Incidencia execute(Long usuarioId, String tipo, String descripcion, String metodoPago, String referenciaExterna) {
        Incidencia incidencia = Incidencia.builder()
            .usuarioId(usuarioId)
            .tipo(tipo)
            .descripcion(descripcion)
            .metodoPago(metodoPago)
            .referenciaExterna(referenciaExterna)
            .status("pending")
            .fechaIncidencia(LocalDateTime.now())
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .build();
        
        return incidenciaRepository.save(incidencia);
    }
}
