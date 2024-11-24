package com.polideportivo.backend_springboot.domain.service;

import com.polideportivo.backend_springboot.domain.exception.DeporteNotFoundException;
import com.polideportivo.backend_springboot.domain.model.Deporte;
import com.polideportivo.backend_springboot.domain.repository.DeporteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeporteServiceImpl implements DeporteService {
    
    private final DeporteRepository repository;
    private final ImageService imageService;

    @Transactional(readOnly = true)
    public List<Deporte> getAllDeportes() {
        // Obtiene todos los deportes
        List<Deporte> deportes = repository.findAll();
        // Asigna imágenes a cada deporte
        deportes.forEach(deporte -> deporte.setImages(
            imageService.getImagesForEntity("App\\Models\\Deporte", deporte.getId())
        ));
        return deportes;
    }

    @Transactional(readOnly = true)
    public Deporte getBySlug(String slug) {
        // Busca el deporte por slug
        Deporte deporte = repository.findBySlug(slug).orElseThrow(DeporteNotFoundException::new);
        // Asigna imágenes al deporte
        deporte.setImages(imageService.getImagesForEntity("App\\Models\\Deporte", deporte.getId()));
        return deporte;
    }
}
