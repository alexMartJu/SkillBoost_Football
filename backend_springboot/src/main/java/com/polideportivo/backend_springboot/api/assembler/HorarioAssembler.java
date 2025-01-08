package com.polideportivo.backend_springboot.api.assembler;

import com.polideportivo.backend_springboot.api.model.horario.HorarioResponse;
import com.polideportivo.backend_springboot.domain.model.Horario;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HorarioAssembler {
    
    private final ModelMapper modelMapper;

    public HorarioResponse toResponse(Horario horario) {
        var response = modelMapper.map(horario, HorarioResponse.class);
        return response;        
    }
}
