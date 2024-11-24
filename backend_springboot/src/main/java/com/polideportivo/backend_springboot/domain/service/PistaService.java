package com.polideportivo.backend_springboot.domain.service;

import com.polideportivo.backend_springboot.domain.model.Pista;

import java.util.List;

public interface PistaService {
    
    List<Pista> getAllPistas();

    Pista getBySlug(final String slug);
}
