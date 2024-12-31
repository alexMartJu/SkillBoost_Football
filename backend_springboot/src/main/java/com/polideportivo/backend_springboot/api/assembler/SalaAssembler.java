package com.polideportivo.backend_springboot.api.assembler;
import com.polideportivo.backend_springboot.api.model.sala.SalaResponse;
import com.polideportivo.backend_springboot.domain.model.Sala;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SalaAssembler {
    
    private final ModelMapper modelMapper;

    public SalaResponse toResponse(Sala sala) {
        var response = modelMapper.map(sala, SalaResponse.class);
        return response;
    }
}
