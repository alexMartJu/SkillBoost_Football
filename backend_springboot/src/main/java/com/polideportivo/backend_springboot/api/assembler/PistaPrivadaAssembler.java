package com.polideportivo.backend_springboot.api.assembler;

import com.polideportivo.backend_springboot.api.model.pistaPrivada.PistaPrivadaResponse;
import com.polideportivo.backend_springboot.domain.model.Image;
import com.polideportivo.backend_springboot.domain.model.PistaPrivada;
import com.polideportivo.backend_springboot.domain.service.ImageService;

import java.util.List;

import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PistaPrivadaAssembler {
    
    private final ImageService imageService;

    public PistaPrivadaResponse toResponse(PistaPrivada pistaPrivada) {
        // Obtener las imágenes asociadas a la pista privada
        List<Image> pistaPrivadaImages = imageService.getImagesForEntity("App\\Models\\PistaPrivada", pistaPrivada.getId());

        // Crear y devolver el DTO con la información de la pista privada y sus imágenes
        return PistaPrivadaResponse.builder()
                .nombre(pistaPrivada.getNombre())
                .slug(pistaPrivada.getSlug())
                .info(pistaPrivada.getInfo())
                .images(pistaPrivadaImages != null ? pistaPrivadaImages : List.of()) // Si no hay imágenes, asignamos una lista vacía
                .build();
    }
}
