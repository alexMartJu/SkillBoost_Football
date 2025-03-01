package com.skillboostfootball.backend_main_springboot.presentation.assemblers.pistas;

import com.skillboostfootball.backend_main_springboot.domain.entities.pistas.Pista;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.pistas.response.PistaResponse;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.pistas.response.PistaWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PistaAssembler {
    
    public PistaResponse toResponse(Pista pista) {
        PistaResponse response = new PistaResponse();
        
        response.setId(pista.getId());
        response.setNombre(pista.getNombre());
        response.setSlug(pista.getSlug());
        response.setDescripcion(pista.getDescripcion());
        response.setTipo(pista.getTipo());
        response.setDimensiones(pista.getDimensiones());
        response.setCapacidad(pista.getCapacidad());
        response.setImages(pista.getImages());
        
        return response;
    }
    
    public PistaWrapper toWrapper(List<Pista> pistas) {
        List<PistaResponse> responseList = pistas.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
                
        return PistaWrapper.builder()
                .pistas(responseList)
                .build();
    }
    
}
