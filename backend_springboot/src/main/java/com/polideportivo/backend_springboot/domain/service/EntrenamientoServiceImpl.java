package com.polideportivo.backend_springboot.domain.service;

import com.polideportivo.backend_springboot.domain.exception.EntrenamientoNotFoundException;
import com.polideportivo.backend_springboot.domain.model.Entrenamiento;
import com.polideportivo.backend_springboot.domain.repository.EntrenamientoRepository;
import com.polideportivo.backend_springboot.infra.spec.EntrenamientoSpecification;
import com.polideportivo.backend_springboot.api.model.entrenamiento.EntrenamientoDataResponse;

import java.util.List;

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
        Entrenamiento entrenamiento = repository.findBySlugAndStatusAndDeletedAtIsNull(slug, "accepted").orElseThrow(EntrenamientoNotFoundException::new);
        // Asigna imágenes al entrenamiento
        entrenamiento.setImages(imageService.getImagesForEntity("App\\Models\\Entrenamiento", entrenamiento.getId()));
        return entrenamiento;
    }

    @Override
    @Transactional(readOnly = true)
    public EntrenamientoDataResponse getEntrenamientoData() {
        // Obtener todos los entrenamientos no eliminados
        List<Entrenamiento> entrenamientos = repository.findByStatusAndDeletedAtIsNull("accepted");

        // Calcular los datos usando Stream API
        Long totalEntrenamientos = (long) entrenamientos.size();
        Integer precioMinimo = entrenamientos.stream().mapToInt(Entrenamiento::getPrecio).min().orElse(0);
        Integer precioMaximo = entrenamientos.stream().mapToInt(Entrenamiento::getPrecio).max().orElse(0);
        Integer duracionMinima = entrenamientos.stream().mapToInt(Entrenamiento::getDuracion).min().orElse(0);
        Integer duracionMaxima = entrenamientos.stream().mapToInt(Entrenamiento::getDuracion).max().orElse(0);
        Integer plazasMinimas = entrenamientos.stream().mapToInt(Entrenamiento::getMaxPlazas).min().orElse(0);
        Integer plazasMaximas = entrenamientos.stream().mapToInt(Entrenamiento::getMaxPlazas).max().orElse(0);

        // Devolver los datos de forma ordenada
        return EntrenamientoDataResponse.builder()
        .totalEntrenamientos(totalEntrenamientos)
        .precioMinimo(precioMinimo)
        .precioMaximo(precioMaximo)
        .duracionMinima(duracionMinima)
        .duracionMaxima(duracionMaxima)
        .plazasMinimas(plazasMinimas)
        .plazasMaximas(plazasMaximas)
        .build();
    }

    @Override
    public void update(Entrenamiento entrenamiento) {
        repository.save(entrenamiento);
    }

}
