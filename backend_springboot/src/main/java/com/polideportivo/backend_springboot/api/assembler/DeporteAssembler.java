package com.polideportivo.backend_springboot.api.assembler;

import com.polideportivo.backend_springboot.api.model.deporte.DeporteWrapper;
import com.polideportivo.backend_springboot.api.model.deporte.DeporteResponse;
import com.polideportivo.backend_springboot.domain.model.Deporte;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DeporteAssembler {
       
    private final ModelMapper modelMapper;

    public DeporteResponse toResponse(Deporte deporte) {
        var response = modelMapper.map(deporte, DeporteResponse.class);
        return response;
    }

    public DeporteWrapper toCollectionModel(List<Deporte> deportes) {

        var content = deportes.stream()
                .map(this::toResponse)
                .toList();

        return buildResponse(content);
    }

    private DeporteWrapper buildResponse(List<DeporteResponse> deportes) {
        return DeporteWrapper.builder()
                .deportes(deportes)
                .build();
    }
}
