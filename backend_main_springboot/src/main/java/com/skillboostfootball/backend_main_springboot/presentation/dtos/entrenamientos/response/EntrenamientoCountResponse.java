package com.skillboostfootball.backend_main_springboot.presentation.dtos.entrenamientos.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EntrenamientoCountResponse {
    private Long count;
}
