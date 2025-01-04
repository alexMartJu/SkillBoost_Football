package com.polideportivo.backend_springboot.domain.service;

import com.polideportivo.backend_springboot.domain.exception.ResourceNotFoundException;
import com.polideportivo.backend_springboot.domain.model.Entrenador;
import com.polideportivo.backend_springboot.domain.repository.EntrenadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EntrenadorServiceImpl implements EntrenadorService {

    private final EntrenadorRepository repository;
    private final ImageService imageService;
    
    @Transactional(readOnly = true)
    public List<Entrenador> getAllEntrenadores() {

        // Obtiene todos los entrenadores no eliminados
        List<Entrenador> entrenadores = repository.findByDeletedAtIsNull();

        // Validar si la lista está vacía
        if (entrenadores.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron entrenadores.");
        }
        
        // Asigna imágenes a cada entrenador
        entrenadores.forEach(entrenador -> entrenador.setImages(
            imageService.getImagesForEntity("App\\Models\\Entrenador", entrenador.getId())
        ));
        return entrenadores;
    }
}
