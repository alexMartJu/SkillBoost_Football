package com.polideportivo.backend_springboot.domain.service;

import com.polideportivo.backend_springboot.domain.model.Entrenamiento;
import com.polideportivo.backend_springboot.infra.spec.EntrenamientoSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.polideportivo.backend_springboot.api.model.entrenamiento.EntrenamientoDataResponse;

public interface EntrenamientoService {
    
    Page<Entrenamiento> getAllEntrenamientos(final EntrenamientoSpecification filter,final Pageable pageable);

    Entrenamiento getEntrenamientoBySlug(final String slug);

    EntrenamientoDataResponse getEntrenamientoData();
}
