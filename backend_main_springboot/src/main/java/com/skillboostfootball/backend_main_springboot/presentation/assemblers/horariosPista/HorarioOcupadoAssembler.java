package com.skillboostfootball.backend_main_springboot.presentation.assemblers.horariosPista;

import com.skillboostfootball.backend_main_springboot.domain.entities.horariosPista.HorarioPista;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.horariosPista.response.HorarioOcupadoResponse;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.horariosPista.response.HorariosOcupadosWrapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class HorarioOcupadoAssembler {
    
    public HorarioOcupadoResponse toResponse(HorarioPista horarioPista) {
        return HorarioOcupadoResponse.builder()
            .id(horarioPista.getId())
            .fechaInicio(horarioPista.getFechaInicio())
            .fechaFin(horarioPista.getFechaFin())
            .build();
    }
    
    public HorariosOcupadosWrapper toWrapper(List<HorarioPista> horarios) {
        List<HorarioOcupadoResponse> responseList = horarios.stream()
            .map(this::toResponse)
            .collect(Collectors.toList());
                
        return HorariosOcupadosWrapper.builder()
            .horariosOcupados(responseList)
            .build();
            
    }
}
