package com.skillboostfootball.backend_main_springboot.application.useCases.suscripciones;

import com.skillboostfootball.backend_main_springboot.domain.entities.suscripciones.Suscripcion;
import com.skillboostfootball.backend_main_springboot.domain.exceptions.suscripciones.SuscripcionNotFoundException;
import com.skillboostfootball.backend_main_springboot.domain.repositories.suscripciones.SuscripcionRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllSuscripcionesUseCase {
    private final SuscripcionRepository suscripcionRepository;
    
    @Transactional(readOnly = true)
    public List<Suscripcion> execute() {

        List<Suscripcion> suscripciones = suscripcionRepository.findAll();
        
        if (suscripciones.isEmpty()) {
            throw new SuscripcionNotFoundException();
        }
        
        return suscripciones;
        
    }
}
