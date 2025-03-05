package com.skillboostfootball.backend_main_springboot.application.useCases.suscripciones;

import com.skillboostfootball.backend_main_springboot.domain.entities.suscripciones.Suscripcion;
import com.skillboostfootball.backend_main_springboot.domain.exceptions.suscripciones.SuscripcionNotFoundException;
import com.skillboostfootball.backend_main_springboot.domain.repositories.suscripciones.SuscripcionRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class UpdateSuscripcionPrecioUseCase {
    private final SuscripcionRepository suscripcionRepository;
    
    @Transactional
    public Suscripcion execute(String slug, BigDecimal precio) {

        Suscripcion suscripcion = suscripcionRepository.findBySlug(slug).orElseThrow(SuscripcionNotFoundException::new);
        suscripcion.updatePrecio(precio);

        return suscripcionRepository.save(suscripcion);
    }
}
