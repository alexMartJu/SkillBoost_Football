package com.polideportivo.backend_springboot.domain.service;

import com.polideportivo.backend_springboot.domain.exception.PistaNotFoundException;
import com.polideportivo.backend_springboot.domain.model.Pista;
import com.polideportivo.backend_springboot.domain.repository.PistaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PistaServiceImpl implements PistaService {
    private final PistaRepository repository;
    private final ImageService imageService;

    @Transactional(readOnly = true)
    public List<Pista> getAllPistas() {
        // Obtiene todas las pistas
        List<Pista> pistas = repository.findByDeletedAtIsNull();
        // Asigna imágenes a cada pista
        pistas.forEach(pista -> pista.setImages(
            imageService.getImagesForEntity("App\\Models\\Pista", pista.getId())
        ));
        return pistas;
    }

    @Transactional(readOnly = true)
    public Pista getBySlug(String slug) {
        // Busca la pista por slug
        Pista pista = repository.findBySlugAndDeletedAtIsNull(slug).orElseThrow(PistaNotFoundException::new);
        // Asigna imágenes a la pista
        pista.setImages(imageService.getImagesForEntity("App\\Models\\Pista", pista.getId()));
        return pista;
    }
}
