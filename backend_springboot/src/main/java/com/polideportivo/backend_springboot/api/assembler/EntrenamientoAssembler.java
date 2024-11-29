package com.polideportivo.backend_springboot.api.assembler;

import com.polideportivo.backend_springboot.api.model.entrenamiento.EntrenamientoWrapper;
import com.polideportivo.backend_springboot.api.model.entrenamiento.EntrenamientoResponse;
import com.polideportivo.backend_springboot.api.model.entrenamiento.EntrenamientoDataResponse;
import com.polideportivo.backend_springboot.domain.model.Entrenamiento;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EntrenamientoAssembler {
    private final ModelMapper modelMapper;

    public EntrenamientoResponse toResponse(Entrenamiento entrenamiento) {
        var response = modelMapper.map(entrenamiento, EntrenamientoResponse.class);
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

}
