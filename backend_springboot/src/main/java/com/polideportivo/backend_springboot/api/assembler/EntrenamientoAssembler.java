package com.polideportivo.backend_springboot.api.assembler;

import com.polideportivo.backend_springboot.api.model.entrenamiento.EntrenamientoWrapper;
import com.polideportivo.backend_springboot.api.model.pistaPrivada.PistaPrivadaResponse;
import com.polideportivo.backend_springboot.api.model.entrenamiento.EntrenamientoResponse;
import com.polideportivo.backend_springboot.api.model.entrenamiento.EntrenamientoCountResponse;
import com.polideportivo.backend_springboot.api.model.entrenamiento.EntrenamientoDataResponse;
import com.polideportivo.backend_springboot.domain.model.Entrenamiento;
import com.polideportivo.backend_springboot.domain.model.Image;
import com.polideportivo.backend_springboot.domain.service.ImageService;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EntrenamientoAssembler {
    private final ModelMapper modelMapper;
    private final PistaPrivadaAssembler pistaPrivadaAssembler;
    private final ImageService imageService;

    public EntrenamientoResponse toResponse(Entrenamiento entrenamiento) {
        var response = modelMapper.map(entrenamiento, EntrenamientoResponse.class);

        // if (entrenamiento.getPistaPrivada() != null) {
        //     PistaPrivadaResponse pistaPrivadaResponse = pistaPrivadaAssembler.toResponse(entrenamiento.getPistaPrivada());
        //     response.setPistaPrivada(pistaPrivadaResponse);  // Asignamos la pista privada al DTO
        // }
        // Verificamos si el entrenamiento tiene una pista privada asociada
        if (entrenamiento.getPistaPrivada() != null) {
            PistaPrivadaResponse pistaPrivadaResponse = pistaPrivadaAssembler.toResponse(entrenamiento.getPistaPrivada());

            // Obtener las imágenes asociadas a la pista privada
            List<Image> images = imageService.getImagesForEntity("App\\Models\\PistaPrivada", entrenamiento.getPistaPrivada().getId());

            // Establecemos las imágenes en el DTO de la pista privada
            pistaPrivadaResponse.setImages(images);

            // Asignamos la pista privada con sus imágenes al DTO de entrenamiento
            response.setPistaPrivada(pistaPrivadaResponse);
        }

        return response;
    }

    public EntrenamientoWrapper toCollectionModel(List<Entrenamiento> entrenamientos) {

        var content = entrenamientos.stream()
                .map(this::toResponse)
                .toList();

        return buildResponse(content);
    }

    private EntrenamientoWrapper buildResponse(List<EntrenamientoResponse> entrenamientos) {
        return EntrenamientoWrapper.builder()
                .entrenamientos(entrenamientos)
                .build();
    }

    public EntrenamientoDataResponse toDataResponse(
            Long totalEntrenamientos,
            Integer precioMinimo,
            Integer precioMaximo,
            Integer duracionMinima,
            Integer duracionMaxima,
            Integer plazasMinimas,
            Integer plazasMaximas) {

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

    public EntrenamientoCountResponse toCountResponse(int count) {
        return new EntrenamientoCountResponse(count);
    }

}
