package com.skillboostfootball.backend_main_springboot.presentation.dtos.subtiposTecnificacion.response;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class SubtipoTecnificacionWrapper {
    
    @JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
    private List<SubtipoTecnificacionResponse> subtipos;
}