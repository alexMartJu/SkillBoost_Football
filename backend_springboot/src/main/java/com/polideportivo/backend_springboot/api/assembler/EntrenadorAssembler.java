package com.polideportivo.backend_springboot.api.assembler;

import com.polideportivo.backend_springboot.api.model.entrenador.EntrenadorWrapper;
import com.polideportivo.backend_springboot.api.model.deporte.DeporteResponse;
import com.polideportivo.backend_springboot.api.model.entrenador.EntrenadorResponse;
import com.polideportivo.backend_springboot.domain.model.Entrenador;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EntrenadorAssembler {
    
    private final ModelMapper modelMapper;
    private final SalaAssembler salaAssembler;

    public EntrenadorResponse toResponse(Entrenador entrenador) {
        var response = modelMapper.map(entrenador, EntrenadorResponse.class);

        // Asegurarse de que el deporte sea mapeado a DeporteResponse
        DeporteResponse deporteResponse = modelMapper.map(entrenador.getDeporte(), DeporteResponse.class);
        response.setDeporte(deporteResponse);  // Setear el deporte como DeporteResponse

        // Si el entrenador tiene una sala, mapeamos la información de la sala
        if (entrenador.getSala() != null) {
            response.setSala(salaAssembler.toResponse(entrenador.getSala())); // Setear la sala asociada
        }
        
        return response;
    }

    public EntrenadorWrapper toCollectionModel(List<Entrenador> entrenadores) {

        var content = entrenadores.stream()
                .map(this::toResponse)
                .toList();

        return buildResponse(content);
    }

    private EntrenadorWrapper buildResponse(List<EntrenadorResponse> entrenadores) {
        return EntrenadorWrapper.builder()
                .entrenadores(entrenadores)
                .build();
    }
}
