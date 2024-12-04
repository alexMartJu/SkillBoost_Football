package com.polideportivo.backend_springboot.api.assembler;

import com.polideportivo.backend_springboot.api.model.pista.PistaWrapper;
import com.polideportivo.backend_springboot.api.model.pista.PistaResponse;
import com.polideportivo.backend_springboot.domain.model.Pista;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PistaAssembler {
    
    private final ModelMapper modelMapper;

    public PistaResponse toResponse(Pista pista) {
        System.out.println("Convirtiendo pista: " + pista); 
        var response = modelMapper.map(pista, PistaResponse.class);

        return response;
    }

    public PistaWrapper toCollectionModel(List<Pista> pistas) {

        var content = pistas.stream()
                .map(this::toResponse)
                .toList();

        return buildResponse(content);
    }

    private PistaWrapper buildResponse(List<PistaResponse> pistas) {
        return PistaWrapper.builder()
                .pistas(pistas)
                .build();
    }
}
