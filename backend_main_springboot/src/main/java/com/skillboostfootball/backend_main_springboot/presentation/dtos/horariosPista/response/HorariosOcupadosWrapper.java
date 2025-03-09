package com.skillboostfootball.backend_main_springboot.presentation.dtos.horariosPista.response;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Builder
public class HorariosOcupadosWrapper {
    @JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
    private List<HorarioOcupadoResponse> horariosOcupados;
}
