package com.polideportivo.backend_springboot.domain.service;

import com.polideportivo.backend_springboot.domain.exception.DeporteNotFoundException;
import com.polideportivo.backend_springboot.domain.model.Deporte;
import com.polideportivo.backend_springboot.domain.model.Pista;
import com.polideportivo.backend_springboot.domain.repository.DeporteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeporteServiceImpl implements DeporteService {
    
    private final DeporteRepository repository;
    private final ImageService imageService;

    @Transactional(readOnly = true)
    public List<Deporte> getAllDeportes() {
        // Obtiene todos los deportes
        List<Deporte> deportes = repository.findByDeletedAtIsNull();

        // Validar si la lista está vacía
        if (deportes.isEmpty()) {
            throw new DeporteNotFoundException(); // Se lanza la excepción con el mensaje predeterminado
        }
        
        // Asigna imágenes a cada deporte y a las pistas asociadas
        deportes.forEach(deporte -> {
            // Cargar imágenes del deporte
            deporte.setImages(
                imageService.getImagesForEntity("App\\Models\\Deporte", deporte.getId())
            );

            // Filtrar las pistas asociadas que no están eliminadas
            List<Pista> pistasNoEliminadas = deporte.getPistas().stream()
                .filter(pista -> pista.getDeletedAt() == null) // Filtramos las pistas no eliminadas
                .collect(Collectors.toList());

            // Cargar imágenes de las pistas asociadas al deporte
            pistasNoEliminadas.forEach(pista -> 
                pista.setImages(
                    imageService.getImagesForEntity("App\\Models\\Pista", pista.getId())
                )
            );
            // Establecer las pistas no eliminadas en el deporte
            deporte.setPistas(pistasNoEliminadas);
        });
        return deportes;
    }

    @Transactional
    public Deporte getBySlug(String slug) {
        // Busca el deporte por slug
        Deporte deporte = repository.findBySlugAndDeletedAtIsNull(slug).orElseThrow(DeporteNotFoundException::new);
        
        // Asigna imágenes al deporte
        deporte.setImages(imageService.getImagesForEntity("App\\Models\\Deporte", deporte.getId()));

        // Filtrar las pistas asociadas que no están eliminadas
        List<Pista> pistasNoEliminadas = deporte.getPistas().stream()
                .filter(pista -> pista.getDeletedAt() == null) // Filtramos las pistas no eliminadas
                .collect(Collectors.toList());

        // Cargar imágenes de las pistas asociadas
        pistasNoEliminadas.forEach(pista -> 
            pista.setImages(
                imageService.getImagesForEntity("App\\Models\\Pista", pista.getId())
            )
        );

        // Establecer las pistas no eliminadas en el deporte
        deporte.setPistas(pistasNoEliminadas);
        
        return deporte;
    }
}
