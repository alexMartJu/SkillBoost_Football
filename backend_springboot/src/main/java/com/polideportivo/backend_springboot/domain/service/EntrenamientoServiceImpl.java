package com.polideportivo.backend_springboot.domain.service;

import com.polideportivo.backend_springboot.domain.exception.EntrenamientoNotFoundException;
import com.polideportivo.backend_springboot.domain.model.Entrenamiento;
import com.polideportivo.backend_springboot.domain.repository.EntrenamientoRepository;
import com.polideportivo.backend_springboot.infra.spec.EntrenamientoSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class EntrenamientoServiceImpl implements EntrenamientoService {
    private final EntrenamientoRepository repository;
    private final ImageService imageService;

    @Transactional(readOnly = true)
    public Page<Entrenamiento> getAllEntrenamientos(EntrenamientoSpecification filter, Pageable pageable) {
        // return repository.findAll(spec, pageable);
        Page<Entrenamiento> entrenamientos = repository.findAll(filter, pageable);
        // Asigna imágenes a cada entrenamiento
        entrenamientos.forEach(entrenamiento -> entrenamiento.setImages(
            imageService.getImagesForEntity("App\\Models\\Entrenamiento", entrenamiento.getId())
        ));
        return entrenamientos;
    }

    @Transactional(readOnly = true)
    public Entrenamiento getEntrenamientoBySlug(String slug) {
        // Busca el entrenamiento por slug
        Entrenamiento entrenamiento = repository.findBySlugAndDeletedAtIsNull(slug).orElseThrow(EntrenamientoNotFoundException::new);
        // Asigna imágenes al entrenamiento
        entrenamiento.setImages(imageService.getImagesForEntity("App\\Models\\Entrenamiento", entrenamiento.getId()));
        return entrenamiento;
    }

}
