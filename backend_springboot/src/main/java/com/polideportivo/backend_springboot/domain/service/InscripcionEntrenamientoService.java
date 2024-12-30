package com.polideportivo.backend_springboot.domain.service;

import com.polideportivo.backend_springboot.domain.model.InscripcionEntrenamiento;

public interface InscripcionEntrenamientoService {
    
    // Método para inscribir a un usuario en un entrenamiento
    InscripcionEntrenamiento inscribir(String slug);

    // Método para desinscribir a un usuario de un entrenamiento
    String desinscribir(String slug);
}
