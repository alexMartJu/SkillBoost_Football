package com.polideportivo.backend_springboot.domain.service;

import com.polideportivo.backend_springboot.api.model.pista.PistaReservaStatusResponse;
import com.polideportivo.backend_springboot.domain.model.Pista;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PistaService {
    
    List<Pista> getAllPistas();

    Pista getBySlug(final String slug);

    Page<Pista> getAllScrollPistas(final Pageable pageable);

    List<Pista> getPistasReservadasCurrentUser();

    List<PistaReservaStatusResponse> getProfilePistasStatus();
}
