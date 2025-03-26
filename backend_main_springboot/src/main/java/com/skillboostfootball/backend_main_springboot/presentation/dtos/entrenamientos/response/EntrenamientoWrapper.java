package com.skillboostfootball.backend_main_springboot.presentation.dtos.entrenamientos.response;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class EntrenamientoWrapper {
    @JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
    private List<EntrenamientoResponse> entrenamientos;
}
